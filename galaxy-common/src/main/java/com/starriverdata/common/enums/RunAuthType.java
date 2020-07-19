package com.starriverdata.common.enums;

public enum RunAuthType {

    /**
     * 任务类型
     */
    JOB("job"),
    /**
     * 组类型
     */
    GROUP("group");

    private String name;


    RunAuthType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}