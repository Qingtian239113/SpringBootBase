package com.kepai.app.pojos.table;

import com.kepai.app.enums.UserSex;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author huang
 * @ProjectName base
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2018/4/5
 * @note 这里写文件的详细功能和改动
 * @note
 */
@Table(name = "elderly_user")
public class UserTable extends BaseTable {

    private String userNick;
    private String tel;
    private String avatar;
    private String password;
    private UserSex sex;
    private String freeze;
    private Date registerTime;
    private Date lastLoginTime;
    private String terminal;

    ///////////////////////////////////////////////////////////////////////////
    // setter,getter
    ///////////////////////////////////////////////////////////////////////////

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserSex getSex() {
        return sex;
    }

    public void setSex(UserSex sex) {
        this.sex = sex;
    }

    public String getFreeze() {
        return freeze;
    }

    public void setFreeze(String freeze) {
        this.freeze = freeze;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
}
