package com.starriverdata.monitor.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlarmInfo {

    private String message;
    private String userId;
    private String phone;
}