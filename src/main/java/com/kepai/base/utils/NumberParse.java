package com.kepai.base.utils;

/**
 * @author huang
 * @ProjectName base
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 字符串转换成数字类型的方法，防止字符串为null或非数字字符串时出现崩溃
 * @data 16/11/2
 * @note 这里写文件的详细功能和改动
 * @note
 */

public class NumberParse {

    /**
     * 转换成整型
     *
     * @param val
     * @param def
     */
    public static int parseInt(Object val, int def) {
        int result = def;
        try {
            result = Integer.parseInt(val.toString());
        } catch (Exception e) {
            result = def;
        }
        return result;
    }

    /**
     * 转换成整型
     *
     * @param val
     */
    public static int parseInt(Object val) {
        return parseInt(val, 0);
    }

    /**
     * 转换成浮点型
     *
     * @param val
     */
    public static float parseFloat(Object val, float def) {
        float result = def;
        try {
            result = Float.parseFloat(val.toString());
        } catch (Exception e) {
            result = def;
        }
        return result;
    }

    /**
     * 转换成浮点型
     *
     * @param val
     */
    public static float parseFloat(Object val) {
        return parseFloat(val, 0f);
    }

    /**
     * 转换成长整型
     *
     * @param val
     */
    public static Long parseLong(String val, Long def) {
        Long result = def;
        try {
            result = Long.parseLong(val);
        } catch (Exception e) {
            result = def;
        }
        return result;
    }

    /**
     * 转换成长整型
     *
     * @param val
     */
    public static Long parseLong(String val) {
        return parseLong(val, 0L);
    }


}
