package com.starriverdata.common.enums;

public enum JobScheduleTypeEnum {
    /**
     * 定时任务
     */
    Independent(0),

    /**
     * 依赖任务
     */
    Dependent(1);
    private Integer type;

    JobScheduleTypeEnum(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }


    public static JobScheduleTypeEnum parser(Integer v) {
        for (JobScheduleTypeEnum t : JobScheduleTypeEnum.values()) {
            if (t.getType().equals(v)) {
                return t;
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }
}

