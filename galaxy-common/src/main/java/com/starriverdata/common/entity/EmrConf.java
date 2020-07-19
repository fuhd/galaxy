package com.starriverdata.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * emr信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmrConf {
    private String loginURl;

    private String clusterName;

    private String masterInstanceType;

    private int numCoreNodes;

    private int numTaskNodes;

    private String coreInstanceType;

    private String taskInstanceType;

    private String emrManagedMasterSecurityGroup;

    private String emrManagedSlaveSecurityGroup;

    private String additionalMasterSecurityGroups;

    private String additionalSlaveSecurityGroups;

    private String serviceAccessSecurityGroup;

    private String ec2SubnetId;

    private String keyPairName;

}

