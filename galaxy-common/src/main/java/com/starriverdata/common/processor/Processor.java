package com.starriverdata.common.processor;

import java.io.Serializable;

/**
 * 可以提供Job运行的前置处理或者后置处理,处理日志运行日志
 */
public interface Processor extends Serializable {

    String getId();

    String getConfig();

    void parse(String config);

}
