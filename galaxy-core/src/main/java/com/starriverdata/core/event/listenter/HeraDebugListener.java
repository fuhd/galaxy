package com.starriverdata.core.event.listenter;


import com.starriverdata.common.service.HeraFileService;
import com.starriverdata.event.HeraDebugFailEvent;
import com.starriverdata.event.HeraDebugSuccessEvent;
import com.starriverdata.core.event.base.MvcEvent;
import com.starriverdata.core.netty.master.MasterContext;

public class HeraDebugListener extends AbstractListener {

    private HeraFileService heraFileService;

    public HeraDebugListener(MasterContext masterContext) {
        heraFileService = masterContext.getHeraFileService();
    }

    @Override
    public void beforeDispatch(MvcEvent mvcEvent) {
        if (mvcEvent.getApplicationEvent() instanceof HeraDebugFailEvent) {

        } else if (mvcEvent.getApplicationEvent() instanceof HeraDebugSuccessEvent) {

        }
    }
}
