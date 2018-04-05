package com.kepai.base.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huang
 * @ProjectName base
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2018/4/4
 * @note 这里写文件的详细功能和改动
 * @note
 */
final public class BeanHelper {

    /**
     * 复制属性
     *
     * @param object
     * @param n
     * @param <O>
     * @param <N>
     * @return
     */
    public static <O, N> N copy(O object, Class<N> n, OnCopyAfter<O, N> after) throws Exception {
        N dto = n.newInstance();
        BeanUtils.copyProperties(object, dto);
        if (after != null) {
            after.after(object, dto);
        }
        return dto;
    }

    public static <O, N> N copy(O object, Class<N> n) {
        try {
            return copy(object, n, null);
        } catch (Exception e) {
            return null;
        }
    }

    public static <O, N> List<N> copy(List<O> oo, Class<N> n) {
        List<N> dtos = new ArrayList<>();
        for (O o : oo) {
            try {
                N dto = copy(o, n, null);
                dtos.add(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dtos;
    }

    public static <O, N> List<N> copy(List<O> oo, Class<N> n, OnCopyAfter<O, N> after) {
        List<N> dtos = new ArrayList<>();
        for (O o : oo) {
            try {
                N dto = copy(o, n, after);
                dtos.add(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dtos;
    }

    ///////////////////////////////////////////////////////////////////////////
    // 回调接口
    ///////////////////////////////////////////////////////////////////////////

    public interface OnCopyAfter<O, N> {

        /**
         * 属性复制之后回调
         *
         * @param o
         * @param n
         */
        void after(O o, N n);
    }


}
