package com.starriverdata.common.enums;

public enum OperatorSystemEnum {
    /**
     * mac os 系统机器
     */
    MAC,
    /**
     * linux系统机器
     */
    LINUX,
    /**
     * windows系统机器
     */
    WIN;

    public static boolean isLinux(OperatorSystemEnum systemEnum) {
        return systemEnum == LINUX;
    }

    public static boolean isMac(OperatorSystemEnum systemEnum) {
        return systemEnum == MAC;
    }

    public static boolean isWindows(OperatorSystemEnum systemEnum) {
        return systemEnum == WIN;
    }
}
