package com.starriverdata.common.entity.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HeraFileTreeNodeVo {

    Integer id;
    Integer parent;
    String name;
    boolean isParent;

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

}
