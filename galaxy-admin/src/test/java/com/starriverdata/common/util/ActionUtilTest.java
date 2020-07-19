package com.starriverdata.common.util;

import com.starriverdata.common.kv.Tuple;
import org.junit.Test;

import java.util.Date;

public class ActionUtilTest {

    @Test
    public void getNextDayString() {
        Tuple<String, Date> dayString = ActionUtil.getNextDayString();

        System.out.println(dayString.getSource());
        System.out.println(dayString.getTarget());
    }
}