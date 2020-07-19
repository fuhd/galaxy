package com.starriverdata.core.event.listenter;

import com.starriverdata.common.constants.Constants;
import com.starriverdata.common.entity.HeraSso;
import com.starriverdata.common.entity.vo.HeraRerunVo;
import com.starriverdata.common.enums.TriggerTypeEnum;
import com.starriverdata.common.service.EmailService;
import com.starriverdata.common.service.HeraRerunService;
import com.starriverdata.common.service.HeraSsoService;
import com.starriverdata.common.util.ActionUtil;
import com.starriverdata.config.HeraGlobalEnv;
import com.starriverdata.core.event.base.MvcEvent;
import com.starriverdata.core.netty.master.MasterContext;
import com.starriverdata.event.HeraJobFailedEvent;
import com.starriverdata.event.HeraJobSuccessEvent;
import com.starriverdata.logs.ErrorLog;
import com.starriverdata.logs.HeraLog;
import com.starriverdata.monitor.domain.AlarmInfo;
import com.starriverdata.monitor.service.AlarmCenter;

public class HeraJobFinishListener extends AbstractListener {

    private HeraRerunService heraRerunService;

    private AlarmCenter alarmCenter;

    private HeraSsoService heraSsoService;

    private EmailService emailService;


    public HeraJobFinishListener(MasterContext context) {
        heraRerunService = context.getHeraRerunService();
        alarmCenter = context.getAlarmCenter();
        heraSsoService = context.getHeraSsoService();
        emailService = context.getEmailService();
    }


    @Override
    public void beforeDispatch(MvcEvent mvcEvent) {
        doRerunCheck(mvcEvent);
    }

    private void doRerunCheck(MvcEvent mvcEvent) {
        Long actionId = null;
        Integer rerunId = null;
        boolean rerunCheck = false;
        boolean executeSuccess = false;
        if (mvcEvent.getApplicationEvent() instanceof HeraJobSuccessEvent) {
            HeraJobSuccessEvent successEvent = (HeraJobSuccessEvent) mvcEvent.getApplicationEvent();
            if (rerunCheck = successEvent.getTriggerType() == TriggerTypeEnum.AUTO_RERUN) {
                actionId = successEvent.getJobId();
                rerunId = Integer.parseInt(successEvent.getHeraJobHistory().getProperties().get(Constants.RERUN_ID));
                executeSuccess = true;
            }
        }
        if (mvcEvent.getApplicationEvent() instanceof HeraJobFailedEvent) {
            HeraJobFailedEvent failedEvent = (HeraJobFailedEvent) mvcEvent.getApplicationEvent();
            if (rerunCheck = failedEvent.getTriggerType() == TriggerTypeEnum.AUTO_RERUN) {
                actionId = failedEvent.getActionId();
                rerunId = Integer.parseInt(failedEvent.getHeraJobHistory().getProperties().get(Constants.RERUN_ID));
            }
        }
        if (rerunCheck) {
            doRerunCheck(rerunId, actionId, executeSuccess);
        }
    }

    @Override
    public void afterDispatch(MvcEvent mvcEvent) {

    }

    private void doRerunCheck(Integer rerunId, Long actionId, boolean executeSuccess) {

        if (actionId != null) {
            super.getSinglePool().execute(() -> {
                //单线程池 无需担心并发问题
                HeraRerunVo rerunVo = null;
                try {
                    Integer jobId = ActionUtil.getJobId(actionId);
                    //同步问题
                    rerunVo = heraRerunService.findVoById(rerunId);
                    int processNum = Integer.parseInt(rerunVo.getExtra().getOrDefault(Constants.ACTION_PROCESS_NUM, "0"));
                    int failedNum = Integer.parseInt(rerunVo.getExtra().getOrDefault(Constants.ACTION_FAILED_NUM, "0"));

                    rerunVo.getExtra().put(Constants.ACTION_PROCESS_NUM, String.valueOf(++processNum));
                    if (!executeSuccess) {
                        rerunVo.getExtra().put(Constants.ACTION_FAILED_NUM, String.valueOf(++failedNum));
                    }
                    if (rerunVo.getExtra().get(Constants.ACTION_ALL_NUM).equals(rerunVo.getExtra().get(Constants.ACTION_PROCESS_NUM))) {
                        rerunVo.setIsEnd(1);
                        HeraSso sso = heraSsoService.findSsoByName(rerunVo.getSsoName());
                        String noticeMsg = "【重跑任务执行完成】\n"
                                + "重跑区域:" + HeraGlobalEnv.getArea() + "\n"
                                + "任务ID:" + jobId + "\n"
                                + "重跑名称:" + rerunVo.getName() + "\n"
                                + "总执行次数:" + processNum + "\n"
                                + "失败次数:" + failedNum;
                        if (sso != null) {

                            alarmCenter.sendToWeChat(AlarmInfo.builder().userId(sso.getJobNumber()).message(noticeMsg).build());
                            emailService.sendEmail("hera重跑任务执行完成", noticeMsg, sso.getEmail());
                        } else {
                            HeraLog.info(noticeMsg);
                        }
                    }
                } catch (Exception e) {
                    ErrorLog.error("更新重跑个数异常:" + actionId, e);
                } finally {
                    if (rerunVo != null) {
                        heraRerunService.updateById(HeraRerunVo.builder().id(rerunVo.getId()).extra(rerunVo.getExtra()).isEnd(rerunVo.getIsEnd()).build());
                    }
                }
            });
        }

    }
}
