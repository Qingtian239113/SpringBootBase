package com.kepai.app.enums;

/**
 * @author huang
 * @ProjectName base
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2018/4/5
 * @note 这里写文件的详细功能和改动
 * @note
 */
public enum UserSex implements BaseEnum {

    MAN("0"),
    WOMAN("1");

    private String sex;

    UserSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String getJdbcValue() {
        return this.sex;
    }

    ///////////////////////////////////////////////////////////////////////////
    // setter,getter
    ///////////////////////////////////////////////////////////////////////////

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
