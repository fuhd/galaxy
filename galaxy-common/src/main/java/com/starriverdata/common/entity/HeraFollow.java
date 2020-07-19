package com.starriverdata.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeraFollow {

    public static final Integer GroupType=1;
    public static final Integer JobType=2;
    public static final Integer isFirst=1;
    public static final Integer notFirst=0;
    private Long id;
    /**
     * 关注的类型
     * 1：group  2：Job
     */
    private Integer type;
    private String uid;
    /**
     * 关注的目标id
     * 如果关注group  则这里是group id
     * 如果关注的是Job  则这里是Job id
     */
    private String targetId;
    /**
     * false表示不是重要联系人，true表示是重要联系人
     */
    private boolean important;

}
