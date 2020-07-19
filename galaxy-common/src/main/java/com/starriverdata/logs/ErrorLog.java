package com.starriverdata.logs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorLog {

    public static void error(String msg) {
        log.error(msg);
    }

    public static void error(String format, Object... arguments) {
        log.error(format, arguments);
    }

    public static void error(String msg, Exception e) {
        log.error(msg, e);
    }

    public static void warn(String format, Object... arguments) {
        log.warn(format, arguments);
    }
}
