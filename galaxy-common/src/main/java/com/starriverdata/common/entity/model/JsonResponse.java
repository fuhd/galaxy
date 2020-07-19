package com.starriverdata.common.entity.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * http请求返回结果
 */
@Data
@Builder
@NoArgsConstructor
public class JsonResponse implements Serializable {

    private String  message;
    private boolean success;
    private Object  data;

    public JsonResponse(boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    public JsonResponse(boolean success, Object data) {
        this.data = data;
        this.success = success;
    }

    public JsonResponse(String message, boolean success, Object data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }
    public JsonResponse(boolean success, String message, Object data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public Map<String, Object> toMap() {
        return (Map<String, Object>) JSONObject.toJSON(this);
    }

}
