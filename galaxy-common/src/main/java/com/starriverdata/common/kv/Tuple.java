package com.starriverdata.common.kv;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 原始数据格式变为列表，方便构建Job目录树和Job DAG
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tuple<S, T> {

    private S source;

    private T target;


}
