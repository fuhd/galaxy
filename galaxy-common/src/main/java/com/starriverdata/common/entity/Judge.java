package com.starriverdata.common.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Judge {

    public Date lastModified;
    public Integer maxId;
    public Integer count;
    public Date stamp;
}

