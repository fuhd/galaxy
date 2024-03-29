package com.starriverdata.controller;

import com.alibaba.fastjson.JSONObject;
import com.starriverdata.common.entity.HeraFile;
import com.starriverdata.common.entity.HeraSso;
import com.starriverdata.common.entity.HeraUser;
import com.starriverdata.common.entity.model.JsonResponse;
import com.starriverdata.common.entity.model.TableResponse;
import com.starriverdata.common.entity.vo.HeraSsoVo;
import com.starriverdata.common.entity.vo.HeraUserVo;
import com.starriverdata.common.service.HeraFileService;
import com.starriverdata.common.service.HeraSsoService;
import com.starriverdata.common.service.HeraUserService;
import com.starriverdata.common.util.ActionUtil;
import com.starriverdata.config.AdminCheck;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/userManage/")
public class UserManageController {

    @Autowired
    private HeraUserService heraUserService;

    @Autowired
    private HeraSsoService heraSsoService;

    @Autowired
    @Qualifier("heraFileMemoryService")
    private HeraFileService heraFileService;

    @RequestMapping(value = "/initUser", method = RequestMethod.GET)
    @ResponseBody
    public TableResponse initUser() {
        List<HeraUser> users = heraUserService.getAll();
        List<HeraUserVo> res;
        if (users != null) {
            res = new ArrayList<>(users.size());
            for (HeraUser user : users) {
                HeraUserVo userVo = new HeraUserVo();
                BeanUtils.copyProperties(user, userVo);
                userVo.setCreateTime(ActionUtil.getDefaultFormatterDate(user.getGmtCreate()));
                userVo.setOpTime(ActionUtil.getDefaultFormatterDate(user.getGmtModified()));
                res.add(userVo);
            }
        } else {
            res = new ArrayList<>(0);
        }
        res.sort((o1, o2) -> -(o1.getCreateTime().compareTo(o2.getCreateTime())));
        return new TableResponse(res.size(), 0, res);
    }

    @RequestMapping(value = "/sso/update", method = RequestMethod.POST)
    @ResponseBody
    @AdminCheck
    public JsonResponse ssoUpdate(HeraSso sso) {
        boolean success = heraSsoService.updateHeraSsoById(sso);
        return new JsonResponse(success, success ? "更新成功" : "更新失败");
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    @ResponseBody
    @AdminCheck
    public JsonResponse userUpdate(HeraUser user) {
        boolean success = heraUserService.update(user);
        return new JsonResponse(success, success ? "更新成功" : "更新失败");
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    @ResponseBody
    @AdminCheck
    public JsonResponse ssoGroups() {
        List<HeraUser> users = heraUserService.getGroups();
        return new JsonResponse(true, users.stream()
                .map(user -> {
                    JSONObject userVo = new JSONObject();
                    userVo.put("id", user.getId());
                    userVo.put("name", user.getName());
                    return userVo;
                }).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/initSso", method = RequestMethod.GET)
    @ResponseBody
    public TableResponse initSso() {
        List<HeraSso> ssoList = heraSsoService.getAll();
        if (ssoList == null || ssoList.size() == 0) {
            return new TableResponse(-1, "数据为空");
        }
        Map<Integer, String> userMap = Optional.ofNullable(heraUserService.getAll())
                .orElse(new ArrayList<>(0))
                .stream()
                .filter(user -> user.getIsEffective() == 1)
                .collect(Collectors.toMap(HeraUser::getId, HeraUser::getName));
        List<HeraSsoVo> ssoVoList = ssoList.stream().map(sso -> {
            HeraSsoVo ssoVo = new HeraSsoVo();
            BeanUtils.copyProperties(sso, ssoVo);
            ssoVo.setGName(userMap.getOrDefault(sso.getGid(), "佚名"));
            return ssoVo;
        }).collect(Collectors.toList());
        return new TableResponse(ssoVoList.size(), 0, ssoVoList);
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    @ResponseBody
    @AdminCheck
    public JsonResponse editUser(@RequestBody HeraUser user) {
        boolean success = heraUserService.update(user);
        JsonResponse jsonResponse = new JsonResponse(true, "更新成功");
        if (!success) {
            jsonResponse.setMessage("更新失败");
            jsonResponse.setSuccess(false);
        }
        return jsonResponse;
    }

    /**
     * operateType: 1,执行删除操作，2，执行审核通过操作，3，执行审核拒绝操作
     */

    @RequestMapping(value = "/operateUser", method = RequestMethod.POST)
    @ResponseBody
    @AdminCheck
    public JsonResponse operateUser(Integer id, String operateType, Integer userType) {
        JsonResponse response = new JsonResponse(false, "更新失败");
        switch (UserType.parse(userType)) {
            case SSO:
                switch (OperateTypeEnum.parse(operateType)) {
                    case Refuse:
                        if (heraSsoService.setValid(id, 0)) {
                            response.setSuccess(true);
                            response.setMessage("审核拒绝");
                        } else {
                            response.setSuccess(false);
                            response.setMessage("拒绝失败");
                        }
                        break;
                    case Delete:
                        if (heraSsoService.deleteSsoById(id)) {
                            response.setSuccess(true);
                            response.setMessage("删除成功");
                        } else {
                            response.setSuccess(false);
                            response.setMessage("删除失败");
                        }
                        break;
                    case Approve:
                        if (heraSsoService.setValid(id, 1)) {
                            response.setSuccess(true);
                            response.setMessage("审核通过");
                        } else {
                            response.setSuccess(false);
                            response.setMessage("审核通过失败");
                        }
                        break;
                    default:
                        response.setMessage("未知的操作类型");
                        response.setSuccess(false);
                        break;
                }
                break;
            case ADMIN:
                int result;
                switch (OperateTypeEnum.parse(operateType)) {
                    case Refuse:
                        result = heraUserService.updateEffective(id, "0");
                        if (result > 0) {
                            response.setMessage("审核拒绝");
                            response.setSuccess(true);
                        }
                        break;
                    case Delete:
                        result = heraUserService.delete(id);
                        if (result > 0) {
                            response.setMessage("删除成功");
                            response.setSuccess(true);
                        }
                        break;
                    case Approve:
                        result = heraUserService.updateEffective(id, "1");
                        if (result > 0) {
                            HeraUser user = heraUserService.findById(id);
                            if (user != null) {
                                HeraFile file = heraFileService.findDocByOwner(user.getName());
                                if (file == null) {
                                    Integer integer = heraFileService.insert(HeraFile.builder().name("个人文档").owner(user.getName()).type(1).build());
                                    if (integer <= 0) {
                                        return new JsonResponse(false, "新增文档失败，请联系管理员");
                                    }
                                }
                            }
                            response.setMessage("审核通过");
                            response.setSuccess(true);
                        }
                        break;
                    default:
                        response.setMessage("未知的操作类型");
                        response.setSuccess(false);
                        break;
                }
                break;
            default:
                response.setSuccess(false);
                response.setMessage("未知的用户类型");
                break;
        }
        return response;

    }

    private enum OperateTypeEnum {
        /**
         * 删除操作
         */
        Delete("1"),
        /**
         * 同意操作
         */
        Approve("2"),
        /**
         * 拒绝操作
         */
        Refuse("3");
        private String operateType;

        @Override
        public String toString() {
            return operateType;
        }

        OperateTypeEnum(String type) {
            this.operateType = type;
        }

        public static OperateTypeEnum parse(String operateType) {
            Optional<OperateTypeEnum> typeEnum = Arrays.stream(OperateTypeEnum.values())
                    .filter(operate -> operate.toString().equals(operateType))
                    .findAny();
            return typeEnum.orElse(OperateTypeEnum.Refuse);
        }
    }

    private enum UserType {
        /**
         * sso相关操作
         */
        SSO(1),
        /**
         * 管理员相关
         */
        ADMIN(0);
        private Integer userType;


        UserType(Integer type) {
            this.userType = type;
        }

        public static UserType parse(Integer userType) {
            Optional<UserType> typeEnum = Arrays.stream(UserType.values())
                    .filter(operate -> operate.userType.equals(userType))
                    .findAny();
            return typeEnum.orElse(null);
        }
    }
}
