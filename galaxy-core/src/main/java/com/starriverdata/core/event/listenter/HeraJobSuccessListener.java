package com.starriverdata.core.event.listenter;

import com.starriverdata.common.enums.TriggerTypeEnum;
import com.starriverdata.core.event.base.MvcEvent;
import com.starriverdata.core.netty.master.MasterContext;
import com.starriverdata.event.HeraJobSuccessEvent;

public class HeraJobSuccessListener extends AbstractListener {

    public HeraJobSuccessListener(MasterContext context) {
    }

    /**
     * 任务成功的前置操作，可以在这里处理任务成功单未通知下游任务的的前置操作，比如：通知数据质量平台某某任务成功，可以进行检测
     */
    @Override
    public void beforeDispatch(MvcEvent mvcEvent) {
        if(mvcEvent.getApplicationEvent() instanceof HeraJobSuccessEvent) {
            HeraJobSuccessEvent jobSuccessEvent = (HeraJobSuccessEvent) mvcEvent.getApplicationEvent();
            if(jobSuccessEvent.getTriggerType() == TriggerTypeEnum.SCHEDULE) {
                return;
            }
        }
    }
}
