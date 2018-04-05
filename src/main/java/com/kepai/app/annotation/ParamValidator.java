package com.kepai.app.annotation;

import java.lang.annotation.*;

/**
 * @author huang
 * @ProjectName health
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2017/9/5
 * @note 这里写文件的详细功能和改动
 * @note
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamValidator {

    String[] params() default {};

}
