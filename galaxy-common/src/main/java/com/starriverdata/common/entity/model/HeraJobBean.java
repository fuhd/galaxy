package com.starriverdata.common.entity.model;

import com.starriverdata.common.entity.HeraJob;
import com.starriverdata.common.util.HierarchyProperties;
import com.starriverdata.common.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeraJobBean {

    private HeraJob heraJob;

    private HeraGroupBean groupBean;

    private Set<HeraJobBean> upStream;

    private Set<HeraJobBean> downStream;

    public HierarchyProperties getHierarchyProperties() {
        if (groupBean != null) {
            return new HierarchyProperties(groupBean.getHierarchyProperties(), StringUtil.convertStringToMap(heraJob.getConfigs()));
        }
        return new HierarchyProperties(StringUtil.convertStringToMap(heraJob.getConfigs()));
    }

    public List<Map<String, String>> getHierarchyResources() {
        List<String> existList = new ArrayList<>();
        List<Map<String, String>> local = new ArrayList<>(StringUtil.convertResources(heraJob.getResources()));


        for (Map<String, String> map : local) {
            if (map.get("name") != null && !existList.contains(map.get("name"))) {
                existList.add(map.get("name"));
            }
        }
        if (groupBean != null) {
            List<Map<String, String>> parent = groupBean.getHierarchyResources();
            for (Map<String, String> map : parent) {
                if (map.get("name") != null && !existList.contains(map.get("name"))) {
                    existList.add(map.get("name"));
                    local.add(map);
                }
            }
        }
        return local;
    }

}
