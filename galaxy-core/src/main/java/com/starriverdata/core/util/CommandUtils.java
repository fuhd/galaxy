package com.starriverdata.core.util;

import com.starriverdata.common.exception.HeraException;
import com.starriverdata.common.util.Pair;
import com.starriverdata.config.HeraGlobalEnv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandUtils {

    private static final String CHANGE_AUTHORITY = "chmod -R 777 ";
    private static final String RUN_SH_COMMAND = " " + HeraGlobalEnv.getJobShellBin() + " ";
    private static Pattern hostPattern = Pattern.compile("\\w+@[\\w\\.-]+\\s*");

    /**
     * 修改文件权限命令
     *
     * @param filePath 文件路径
     * @return 民营
     */
    public static String changeFileAuthority(String filePath) {
        return CHANGE_AUTHORITY + filePath;
    }

    /**
     * 关闭终端不影响提交的程序
     *
     * @param prefix        切换的用户命令
     * @param shellFilePath 脚本路径
     * @return 命令
     */
    public static String getRunShCommand(String prefix, String shellFilePath) {
        if (HeraGlobalEnv.isLinuxSystem()) {
            return "setsid " + prefix + RUN_SH_COMMAND + shellFilePath;
        }
        return prefix + RUN_SH_COMMAND + shellFilePath;
    }

    public static Pair<String, String> parseCmd(String loginCmd) throws HeraException {
        String scpCmd = loginCmd.replace("ssh", "scp");
        Matcher matcher = hostPattern.matcher(scpCmd);
        String loginStr;
        if (matcher.find()) {
            loginStr = matcher.group(0);
        } else {
            throw new HeraException("查找ip失败" + scpCmd);
        }
        String prefix = scpCmd.replace(loginStr, "").replace("-p", "-P") + " -r ";

        return Pair.of(prefix, loginStr);
    }
}
