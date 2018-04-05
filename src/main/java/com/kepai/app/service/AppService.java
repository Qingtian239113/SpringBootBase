package com.kepai.app.service;

import com.kepai.app.pojos.table.BaseTable;
import com.kepai.base.crud.CrudService;

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
public class AppService<T extends BaseTable> extends CrudService<T> {

    @Override
    protected void dataValidator(T t) {
        super.dataValidator(t);
        if (t.getId() == null) {
            t.setCreateTime(new Date());
        }
        t.setUpdateTime(new Date());
    }
}
