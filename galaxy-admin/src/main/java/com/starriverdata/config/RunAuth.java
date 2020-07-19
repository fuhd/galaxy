package com.starriverdata.config;

import com.starriverdata.common.enums.RunAuthType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RunAuth {

    /**
     * 需要赋权的类型
     */
    RunAuthType authType() default RunAuthType.JOB;

    /**
     * id 的下标 -1表示第一个参数是vo
     */
    int idIndex() default 0;

    /**
     * 赋权类型的下标，会覆盖authType
     */
    int typeIndex() default -1;
}
