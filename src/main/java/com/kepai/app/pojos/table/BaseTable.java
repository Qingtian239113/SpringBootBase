package com.kepai.app.pojos.table;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author huang
 * @ProjectName base
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2018/4/4
 * @note 这里写文件的详细功能和改动
 * @note
 */
public class BaseTable implements Serializable {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private Date createTime;
    private Date updateTime;

    ///////////////////////////////////////////////////////////////////////////
    // setter,getter
    ///////////////////////////////////////////////////////////////////////////
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
