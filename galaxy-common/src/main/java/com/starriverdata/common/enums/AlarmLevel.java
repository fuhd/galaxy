package com.starriverdata.common.enums;

import java.util.Arrays;
import java.util.Optional;

public enum AlarmLevel {
    /**
     * 发邮件
     */
    EMAIL(0, "邮件"),

    /**
     * 企业微信告警
     */
    WE_CHAT(1, "微信"),

    /**
     * 打电话
     */
    PHONE(2, "电话");

    private Integer level;
    private String name;

    AlarmLevel(Integer level, String name) {
        this.level = level;
        this.name = name;
    }

    public static boolean lessThan(Integer level, AlarmLevel alarmLevel) {
        return Optional.ofNullable(level).orElse(AlarmLevel.PHONE.level) < alarmLevel.level;
    }


    public static AlarmLevel parseByLevel(Integer level) {
        return Arrays.stream(AlarmLevel.values())
                .filter(alarmLevel -> alarmLevel.level.equals(level))
                .findAny()
                .orElse(AlarmLevel.PHONE);
    }

    public static String getName(Integer level) {
        return parseByLevel(level).name;
    }

    public static String getName(AlarmLevel level) {
        return level.name;
    }
}