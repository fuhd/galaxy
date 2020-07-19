package com.starriverdata.common.constants;

/**
 * Job运行所需要的配置信息
 */
public class RunningJobKeyConstant {

    /**
     * 需要执行的shell文件路径
     */
    public static final String RUN_SHELL_PATH = "shell.localfile";

    /**
     * 需要执行的hive文件路径
     */
    public static final String RUN_HIVE_PATH = "hive.localfile";

    /**
     * 需要执行的spark文件路径
     */
    public static final String RUN_SPARK_PATH = "spark.localfile";

    /**
     * 需要执行的spark2文件路径
     */
    public static final String RUN_SPARK2_PATH = "spark2.localfile";

    /**
     * 任务类型
     */
    public static final String JOB_RUN_TYPE = "job.jobtype";

    public static final String JOB_SCRIPT = "job.script";

    /**
     * 依赖周期key
     */
    public static final String DEPENDENCY_CYCLE = "hera.dependency.cycle";

    /**
     * 依赖周期value
     */
    public static final String DEPENDENCY_CYCLE_VALUE = "sameday";


}
