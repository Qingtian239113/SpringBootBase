package com.kepai.app.pojos;

import com.alibaba.druid.util.StringUtils;
import com.kepai.base.HttpResult;
import com.kepai.base.ResultException;
import com.kepai.base.encrypt.PasswordUtils;

/**
 * @author huang
 * @ProjectName base
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2018/4/4
 * @note 这里写文件的详细功能和改动
 * @note
 */
public class LoginToken {

    /**
     * 设备标志，4位
     */
    private LoginTerminal terminal;

    /**
     * 用户id，不定长度
     */
    private Integer userId;

    /**
     * 时间（16进制）,6个字符
     */
    private String startHex = "";

    /**
     * md5加密，32个字符
     */
    private String md5Str;

    public LoginToken(LoginTerminal terminal, Integer userId) {
        this.terminal = terminal;
        this.userId = userId;
        this.startHex = Long.toHexString(System.currentTimeMillis() / 1000);
        try {
            this.md5Str = PasswordUtils.encodeMd5(this.terminal.getTerminal() + this.userId + this.startHex).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private LoginToken(LoginTerminal terminal, Integer userId, String startHex, String md5Str) {
        this.terminal = terminal;
        this.userId = userId;
        this.startHex = startHex;
        this.md5Str = md5Str;
    }

    /**
     * 构建AppAccessToken
     *
     * @param str
     * @return
     */
    public static LoginToken parseToken(String str) {
        try {
            int length = str.length();
            LoginTerminal prefix = LoginTerminal.valueOf(str.substring(0, 4).toUpperCase());
            Integer userId = Integer.parseInt(str.substring(4, length - 32 - 8));
            String startHex = str.substring(length - 32 - 8, length - 32);
            String encryStr = str.substring(length - 32, length);
            if (!StringUtils.equals(encryStr, PasswordUtils.encodeMd5(prefix.getTerminal() + userId + startHex).toUpperCase())) {
                throw new ResultException(HttpResult.respNeedLogin());
            }
            return new LoginToken(prefix, userId, startHex, encryStr);
        } catch (Exception e) {
            throw new ResultException(HttpResult.respNeedLogin());
        }
    }

    /**
     * 转化为appToken
     *
     * @return
     */
    public String toToken() {
        return terminal.getTerminal() + userId + startHex + md5Str;
    }

    ///////////////////////////////////////////////////////////////////////////
    // 内部类
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 登录类型
     */
    public enum LoginTerminal {

        APPS("apps"),
        WEBS("webs"),
        ADMI("admi"),;

        private String terminal;

        LoginTerminal(String terminal) {
            this.terminal = terminal;
        }

        public String getTerminal() {
            return terminal;
        }

        public void setTerminal(String terminal) {
            this.terminal = terminal;
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // setter,getter
    ///////////////////////////////////////////////////////////////////////////

    public LoginTerminal getTerminal() {
        return terminal;
    }

    public void setTerminal(LoginTerminal terminal) {
        this.terminal = terminal;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStartHex() {
        return startHex;
    }

    public void setStartHex(String startHex) {
        this.startHex = startHex;
    }

    public String getMd5Str() {
        return md5Str;
    }

    public void setMd5Str(String md5Str) {
        this.md5Str = md5Str;
    }

}
