package com.starriverdata.core.event.handler;

import com.starriverdata.common.util.StringUtil;
import org.junit.Test;

public class JobHandlerTest {

    @Test
    public void getActionId() {

        boolean b = StringUtil.actionIdToJobId("201807060000000001", "1");
        System.out.println(b);

    }
}