package com.starriverdata.controller;

import com.starriverdata.common.constants.Constants;
import com.starriverdata.common.entity.HeraRecord;
import com.starriverdata.common.enums.LogTypeEnum;
import com.starriverdata.common.enums.RecordTypeEnum;
import com.starriverdata.common.service.HeraRecordService;
import com.starriverdata.common.util.NamedThreadFactory;
import com.starriverdata.config.HeraGlobalEnv;
import com.starriverdata.core.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class BaseHeraController {


    private static ThreadPoolExecutor poolExecutor;
    private static ThreadLocal<HttpServletRequest> requestThread = new ThreadLocal<>();
    @Autowired
    protected HeraRecordService recordService;

    {
        poolExecutor = new ThreadPoolExecutor(
                1, Runtime.getRuntime().availableProcessors() * 4, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new NamedThreadFactory("updateJobThread"), new ThreadPoolExecutor.AbortPolicy());
        poolExecutor.allowCoreThreadTimeOut(true);
    }

    public static void remove() {
        requestThread.remove();
    }

    protected String getIp() {
        String unKnow = "unknown";
        HttpServletRequest request = requestThread.get();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || unKnow.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unKnow.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unKnow.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || unKnow.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || unKnow.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @ModelAttribute
    protected void setRequest(HttpServletRequest request) {
        requestThread.set(request);
    }

    protected String getOwner() {
        return JwtUtils.getObjectFromToken(Constants.TOKEN_NAME, requestThread.get(), Constants.SESSION_USERNAME);
    }

    protected String getSsoName() {
        return JwtUtils.getObjectFromToken(Constants.TOKEN_NAME, requestThread.get(), Constants.SESSION_SSO_NAME);
    }

    protected String getOwnerId() {
        return JwtUtils.getObjectFromToken(Constants.TOKEN_NAME, requestThread.get(), Constants.SESSION_USER_ID);
    }

    protected String getSsoId() {
        return JwtUtils.getObjectFromToken(Constants.TOKEN_NAME, requestThread.get(), Constants.SESSION_SSO_ID);
    }

    protected void addRecord(LogTypeEnum jobType, Integer jobId, String content, RecordTypeEnum typeEnum, String owner, String ownerId) {
        recordService.add(HeraRecord.builder()
                .logId(jobId)
                .logType(jobType.getName())
                .content(content)
                .sso(owner)
                .gid(Integer.parseInt(ownerId))
                .type(typeEnum.getId())
                .build());
    }


    protected void addJobRecord(Integer jobId, String content, RecordTypeEnum typeEnum, String owner, String ownerId) {
        recordService.add(HeraRecord.builder()
                .logId(jobId)
                .logType(LogTypeEnum.JOB.getName())
                .content(content)
                .sso(owner)
                .gid(Integer.parseInt(ownerId))
                .type(typeEnum.getId())
                .build());
    }


    protected void addGroupRecord(Integer jobId, String content, RecordTypeEnum typeEnum, String owner, String ownerId) {
        recordService.add(HeraRecord.builder()
                .logId(jobId)
                .logType(LogTypeEnum.GROUP.getName())
                .content(content)
                .sso(owner)
                .gid(Integer.parseInt(ownerId))

                .type(typeEnum.getId())
                .build());
    }

    protected void addDebugRecord(Integer jobId, String content, RecordTypeEnum typeEnum, String ownerName, String ownerId) {
        recordService.add(HeraRecord.builder()
                .logId(jobId)
                .logType(LogTypeEnum.DEBUG.getName())
                .content(content)
                .sso(ownerName)
                .gid(Integer.parseInt(ownerId))
                .type(typeEnum.getId())
                .build());
    }

    protected void addUserRecord(Integer userId, String content, RecordTypeEnum typeEnum, String ssoName, String ownerId) {
        recordService.add(HeraRecord.builder()
                .logId(userId)
                .logType(LogTypeEnum.USER.getName())
                .content(content)
                .sso(ssoName)
                .gid(Integer.parseInt(ownerId))
                .type(typeEnum.getId())
                .build());
    }

    protected void doAsync(Runnable runnable) {
        poolExecutor.execute(runnable);
    }

    protected boolean isAdmin() {
        return HeraGlobalEnv.getAdmin().equals(getOwner());
    }
}
