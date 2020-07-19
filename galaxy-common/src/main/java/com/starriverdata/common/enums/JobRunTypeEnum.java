package com.starriverdata.common.enums;

public enum JobRunTypeEnum {

    /**
     * shell任务
     */
    Shell("shell"),
    /**
     * hive任务
     */
    Hive("hive"),
    /**
     * spark任务
     */
    Spark("spark"),
    /**
     * spark2任务
     */
    Spark2("spark2");

    private final String id;

    JobRunTypeEnum(String s) {
        this.id = s;
    }

    @Override
    public String toString() {
        return id;
    }

    public static JobRunTypeEnum parser(String v) {
        for (JobRunTypeEnum type : JobRunTypeEnum.values()) {
            if (type.toString().equals(v)) {
                return type;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(JobRunTypeEnum.Shell.toString());
    }
}
