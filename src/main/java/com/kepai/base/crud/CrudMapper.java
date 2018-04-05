package com.kepai.base.crud;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author huang
 * @ProjectName health
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2018/3/2
 * @note 这里写文件的详细功能和改动
 * @note ps：特别注意，该接口不能被扫描到，否则会出错
 */
public interface CrudMapper<T> extends BaseMapper<T>, ExampleMapper<T>, MySqlMapper<T> {

}
