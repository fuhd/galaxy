package com.starriverdata.common.vo;

import com.starriverdata.common.enums.JobStatus;
import com.starriverdata.common.enums.TriggerTypeEnum;
import lombok.Builder;
import lombok.Data;


/**
 * 任务队列中job的实体
 */
@Data
@Builder
public class JobElement {

    /**
     * 版本号id
     */
    private Long jobId;

    private int hostGroupId;

    private boolean fixedEmr;

    private Integer priorityLevel;

    private Long historyId;

    private TriggerTypeEnum triggerType;

    private JobStatus status;

    private boolean isCancel;

    /**
     * 任务的所属组
     */
    private String owner;

    private Integer costMinute;


    public boolean equals(JobElement jobElement) {
        if (jobElement == null || jobElement.getJobId() == null) {
            return false;
        }
        return jobElement.getJobId().equals(jobId);
    }

    @Override
    public int hashCode() {
        return jobId.hashCode();
    }

    @Override
    public String toString() {
        return "JobElement{" +
                "jobId='" + jobId + '\'' +
                ", hostGroupId=" + hostGroupId +
                '}';
    }
}
