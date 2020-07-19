package com.starriverdata.logs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeartLog {
    public static void info(String msg) {
        log.info(msg);
    }

    public static void debug(String msg) {
        log.debug(msg);
    }

    public static void info(String format, Object... arguments) {
        log.info(format, arguments);

    }

    public static void debug(String format, Object... arguments) {
        log.debug(format, arguments);
    }

    public static void error(String msg) {
        log.error(msg);
    }

    public static void error(String format, Object... arguments) {
        log.error(format, arguments);
    }

    public static void error(String msg, Exception e) {
        log.error(msg, e);
    }

    public static void warn(String msg) {
        log.warn(msg);
    }

    public static void warn(String format, Object... arguments) {
        log.warn(format, arguments);
    }

}
