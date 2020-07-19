package com.starriverdata.common.processor;

public class DownProcessor implements Processor {

    @Override
    public String getId() {
        return "download";
    }

    @Override
    public String getConfig() {
        return "";
    }

    @Override
    public void parse(String config) {

    }
}
