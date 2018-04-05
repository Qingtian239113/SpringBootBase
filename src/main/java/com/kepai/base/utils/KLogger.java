package com.kepai.base.utils;

import org.apache.log4j.Logger;

/**
 * @author huang
 * @ProjectName RedWine
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 统一的日志管理
 * @data 2018/3/2
 * @note 这里写文件的详细功能和改动
 * @note
 */
public class KLogger {

    private volatile static Logger logger = null;

    public static Logger getLogger() {
        if (logger == null) {
            synchronized (KLogger.class) {
                if (logger == null) {
                    logger = Logger.getLogger(KLogger.class);
                }
            }
        }
        return logger;
    }

}
