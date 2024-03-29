package com.starriverdata.common.entity.model;

import com.starriverdata.common.entity.vo.HeraGroupVo;
import com.starriverdata.common.util.HierarchyProperties;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Data
public class HeraGroupBean {

    private HeraGroupBean parentGroupBean;
    private HeraGroupVo groupVo;
    private Map<String, HeraJobBean> jobBeanMap;
    private List<HeraGroupBean> child;

    public HierarchyProperties getHierarchyProperties() {
        if (parentGroupBean != null) {
            return new HierarchyProperties(parentGroupBean.getHierarchyProperties(), groupVo.getConfigs());
        }
        return new HierarchyProperties(groupVo.getConfigs());
    }

    public Map<String, String> getProperties() {
        return groupVo.getConfigs();
    }

    public List<Map<String, String>> getHierarchyResources() {
        List<Map<String, String>> local = new ArrayList<>();
        if (groupVo.getResources() != null && groupVo.getResources().size() > 0) {
            local = groupVo.getResources();
        }
        if (parentGroupBean != null) {
            local.addAll(parentGroupBean.getHierarchyResources());
        }
        return local;
    }

    public Map<String, HeraGroupBean> getAllSubGroupBeans() {
        Map<String, HeraGroupBean> map = new HashMap<>(1024);
        for (HeraGroupBean gb : getChild()) {
            for (HeraGroupBean child : getChild()) {
                map.put(String.valueOf(child.getGroupVo().getId()), child);
            }
            map.putAll(gb.getAllSubGroupBeans());
        }
        return map;
    }

    public Map<String, HeraJobBean> getAllSubJobBeans() {
        Map<String, HeraJobBean> map = new HashMap<>(1024);
        for (HeraGroupBean groupBean : getChild()) {
            map.putAll(groupBean.getAllSubJobBeans());
        }
        map.putAll(jobBeanMap);
        return map;
    }
}
