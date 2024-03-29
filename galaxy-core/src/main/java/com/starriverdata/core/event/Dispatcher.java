package com.starriverdata.core.event;

import com.starriverdata.core.event.base.AbstractObservable;
import com.starriverdata.core.event.base.MvcEvent;
import com.starriverdata.core.event.handler.AbstractHandler;
import com.starriverdata.core.event.handler.JobHandler;
import com.starriverdata.core.event.listenter.AbstractListener;
import com.starriverdata.event.ApplicationEvent;
import com.starriverdata.event.EventType;
import com.starriverdata.logs.ErrorLog;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * hera中的任务事件observer, 接受事件，全局广播dispatch
 */
public class Dispatcher extends AbstractObservable {

    public static final EventType beforeDispatch = new EventType();

    public static final EventType afterDispatch = new EventType();

    @Getter
    List<AbstractHandler> jobHandlers;

    public Dispatcher() {
        jobHandlers = new ArrayList<>();
    }

    public void addJobHandler(JobHandler jobHandler) {
        if (!jobHandlers.contains(jobHandler)) {
            jobHandlers.add(jobHandler);
        }
    }

    public void removeJobHandler(JobHandler jobHandler) {
        jobHandlers.remove(jobHandler);
    }

    public void addDispatcherListener(AbstractListener listener) {
        addListener(beforeDispatch, listener);
        addListener(afterDispatch, listener);
    }


    public void forwardEvent(ApplicationEvent event) {
        dispatch(event);
    }

    public void forwardEvent(EventType eventType) {
        dispatch(eventType);
    }


    public void dispatch(EventType type) {
        dispatch(new ApplicationEvent(type));
    }

    public void dispatch(EventType type, Object data) {
        dispatch(new ApplicationEvent(type, data));
    }

    /**
     * 事件广播，每次任务状态变化，触发响应事件，全局广播，自动调度successEvent,触发依赖调度一些依赖更新
     */
    public void dispatch(ApplicationEvent applicationEvent) {
        try {
            MvcEvent mvcEvent = new MvcEvent(this, applicationEvent);
            mvcEvent.setApplicationEvent(applicationEvent);
            if (fireEvent(beforeDispatch, mvcEvent)) {
                List<AbstractHandler> jobHandlersCopy = Lists.newArrayList(jobHandlers);
                for (AbstractHandler jobHandler : jobHandlersCopy) {
                    try {
                        if (jobHandler.canHandle(applicationEvent)) {
                            if (!jobHandler.isInitialized()) {
                                jobHandler.setInitialized(true);
                            }
                            jobHandler.handleEvent(applicationEvent);
                        }
                    } catch (Exception e) {
                        ErrorLog.error(((JobHandler) jobHandler).getActionId() + "广播异常", e);
                    }
                }
                fireEvent(afterDispatch, mvcEvent);
            }
        } catch (Exception e) {
            ErrorLog.error("global dispatch job event error", e);
        }
    }
}
