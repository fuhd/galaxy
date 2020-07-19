package com.starriverdata.core.netty.master.schedule;

import com.starriverdata.common.constants.Constants;
import com.starriverdata.common.entity.HeraAction;
import com.starriverdata.common.entity.HeraJobHistory;
import com.starriverdata.common.entity.vo.HeraRerunVo;
import com.starriverdata.common.enums.TriggerTypeEnum;
import com.starriverdata.common.util.ActionUtil;
import com.starriverdata.common.util.BeanConvertUtils;
import com.starriverdata.common.util.StringUtil;
import com.starriverdata.common.vo.JobElement;
import com.starriverdata.core.netty.ScheduledChore;
import com.starriverdata.core.netty.master.Master;
import com.starriverdata.core.netty.master.MasterWorkHolder;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 重跑任务的触发
 */
public class RerunJobLaunch extends ScheduledChore {

    private Master master;

    private RerunJobLaunch(Master master, long initialDelay, long period, TimeUnit unit) {
        super("RerunJobLaunch", initialDelay, period, unit);
        this.master = master;
    }

    public RerunJobLaunch(Master master) {
        this(master, 60, 60, TimeUnit.SECONDS);
    }

    @Override
    protected void chore() {
        List<HeraRerunVo> rerunVos = master.getMasterContext().getHeraRerunService().findByEnd(0);
        for (HeraRerunVo rerunVo : rerunVos) {
            if (!Boolean.TRUE.toString().equals(rerunVo.getExtra().get(Constants.ACTION_DONE))) {
                continue;
            }
            BlockingQueue<JobElement> manualQueue = new LinkedBlockingQueue<>(master.getMasterContext().getManualQueue());
            int isRunning = 0;
            for (JobElement element : manualQueue) {
                if (Objects.equals(ActionUtil.getJobId(element.getJobId()), rerunVo.getJobId())) {
                    isRunning++;
                }
            }
            for (MasterWorkHolder workHolder : new ArrayList<>(master.getMasterContext().getWorkMap().values())) {
                for (Long aLong : workHolder.getManningRunning()) {
                    if (Objects.equals(ActionUtil.getJobId(aLong.toString()), rerunVo.getJobId())) {
                        isRunning++;
                    }
                }
            }
            int rerunThread = Integer.parseInt(rerunVo.getExtra().getOrDefault(Constants.RERUN_THREAD, "1"));
            if (rerunThread > isRunning) {
                rerunThread -= isRunning;
                Long startAction = rerunVo.getActionNow() == null || rerunVo.getActionNow() == 0L ? ActionUtil.getActionByDateStr(rerunVo.getStartTime()) - 1 : rerunVo.getActionNow();
                Long endAction = ActionUtil.getActionByDateStr(rerunVo.getEndTime());
                List<HeraAction> heraActions = master.getMasterContext().getHeraJobActionService()
                        .findByStartAndEnd(startAction, endAction, rerunVo.getJobId(), rerunThread);
                if (heraActions.size() > 0) {
                    heraActions.stream().sorted(Comparator.comparingLong(HeraAction::getId)).forEach(action -> {
                        HeraJobHistory history = HeraJobHistory.builder().
                                actionId(action.getId()).
                                illustrate("自动重跑").
                                jobId(rerunVo.getJobId()).
                                triggerType(TriggerTypeEnum.AUTO_RERUN.getId()).
                                operator(action.getOwner()).
                                hostGroupId(action.getHostGroupId()).
                                properties(StringUtil.convertMapToString(Collections.singletonMap(Constants.RERUN_ID, String.valueOf(rerunVo.getId())))).
                                build();
                        master.getMasterContext().getHeraJobHistoryService().insert(history);
                        master.run(BeanConvertUtils.convert(history), master.getMasterContext().getHeraJobService().findMemById(rerunVo.getJobId()));
                        master.getMasterContext().getHeraRerunService().updateById(HeraRerunVo.builder()
                                .id(rerunVo.getId())
                                .actionNow(history.getActionId())
                                .build());
                    });
                }
            }
        }
    }
}
