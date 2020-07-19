package com.starriverdata.core.event.listenter;

import com.starriverdata.event.Events;
import com.starriverdata.core.event.base.MvcEvent;

/**
 * 取消初始化事件，放置Job进行出错任务重试，以及开启定时器
 */
public class HeraStopScheduleJobListener extends AbstractListener {

    @Override
    public void beforeDispatch(MvcEvent mvcEvent) {
        if (mvcEvent.getApplicationEvent().getType() == Events.Initialize) {
            mvcEvent.setCancelled(true);
        }
    }
}
