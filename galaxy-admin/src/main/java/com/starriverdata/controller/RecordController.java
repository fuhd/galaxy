package com.starriverdata.controller;

import com.alibaba.fastjson.JSONObject;
import com.starriverdata.common.entity.HeraJob;
import com.starriverdata.common.entity.HeraRecord;
import com.starriverdata.common.entity.HeraUser;
import com.starriverdata.common.entity.model.JsonResponse;
import com.starriverdata.common.entity.model.TablePageForm;
import com.starriverdata.common.entity.model.TableResponse;
import com.starriverdata.common.entity.vo.HeraRecordVo;
import com.starriverdata.common.entity.vo.PageHelper;
import com.starriverdata.common.enums.LogTypeEnum;
import com.starriverdata.common.enums.RecordTypeEnum;
import com.starriverdata.common.service.HeraJobService;
import com.starriverdata.common.service.HeraUserService;
import com.starriverdata.common.util.ActionUtil;
import com.starriverdata.common.util.Pair;
import com.starriverdata.config.HeraGlobalEnv;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/record")
public class RecordController extends BaseHeraController {

    @Autowired
    private HeraUserService heraUserService;

    @Autowired
    private HeraJobService heraJobService;

    @RequestMapping
    public String record() {
        return "/jobManage/record.index";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public TableResponse listRecord(TablePageForm pageForm) {
        String ownerName;
        boolean isAdmin = HeraGlobalEnv.getAdmin().equals(ownerName = getOwner());

        Pair<Integer, List<HeraRecord>> pair;
        Map<Integer, String> cacheOwner = new HashMap<>();
        if (isAdmin) {
            pair = recordService.selectByPage(pageForm);
        } else {
            pair = recordService.selectByPage(pageForm, Integer.parseInt(getOwnerId()));
        }
        List<HeraRecordVo> voList = pair.snd().stream().map(record -> {
            HeraRecordVo recordVo = new HeraRecordVo();
            BeanUtils.copyProperties(record, recordVo);
            recordVo.setCreateTime(ActionUtil.getDefaultFormatterDate(new Date(record.getGmtCreate())));
            recordVo.setType(RecordTypeEnum.parseById(record.getType()).getType());
            if (isAdmin) {
                if (!cacheOwner.containsKey(record.getGid())) {
                    cacheOwner.put(record.getGid(), heraUserService.findById(record.getGid()).getName());
                }
                recordVo.setGName(cacheOwner.get(record.getGid()));
            } else {
                recordVo.setGName(ownerName);
            }
            return recordVo;
        }).collect(Collectors.toList());
        return new TableResponse(pair.fst(), 0, voList);
    }


    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public JsonResponse getRecord(PageHelper pageHelper) {
        Map<Integer, String> cacheOwner = new HashMap<>(2);
        Map<String, Object> res = recordService.findPageByLogId(pageHelper);
        String rows = "rows";
        List<HeraRecordVo> vos = ((List<HeraRecord>) res.get(rows)).stream().map(record -> {
            HeraRecordVo recordVo = new HeraRecordVo();
            BeanUtils.copyProperties(record, recordVo);
            recordVo.setType(RecordTypeEnum.parseById(record.getType()).getType());
            recordVo.setCreateTime(ActionUtil.getDefaultFormatterDate(new Date(record.getGmtCreate())));
            if (!cacheOwner.containsKey(record.getGid())) {
                cacheOwner.put(record.getGid(), Optional.ofNullable(heraUserService.findById(record.getGid())).map(HeraUser::getName).orElse("无"));
            }
            recordVo.setGName(cacheOwner.get(record.getGid()));
            return recordVo;
        }).collect(Collectors.toList());
        res.put(rows, vos);
        return new JsonResponse(true, res);

    }

    @RequestMapping("/now")
    @ResponseBody
    public JsonResponse findNow(Integer logId, String logType, String type) {
        LogTypeEnum typeEnum = LogTypeEnum.parseByName(logType);
        JSONObject resData = new JSONObject();
        String content = "";
        String runType = "Shell";
        switch (typeEnum) {
            case JOB:
                HeraJob heraJob = heraJobService.findById(logId);
                runType = heraJob.getRunType();
                switch (RecordTypeEnum.parseByName(type)) {
                    case SCRIPT:
                    case DELETE:
                        content = heraJob.getScript();
                        break;
                    case RUN_TYPE:
                        content = heraJob.getRunType();
                        break;
                    case CRON:
                        content = heraJob.getCronExpression();
                        break;
                    case CONFIG:
                        content = heraJob.getConfigs();
                        break;
                    case AREA:
                        content = heraJob.getAreaId();
                        break;
                    case SWITCH:
                        content = String.valueOf(heraJob.getAuto());
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        resData.put("content", content);
        resData.put("runType", runType);

        return new JsonResponse(true, resData);
    }
}
