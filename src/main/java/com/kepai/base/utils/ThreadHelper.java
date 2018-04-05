package com.kepai.base.utils;

import java.util.concurrent.*;

/**
 * @author huang
 * @ProjectName health
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2017/12/11
 * @note 这里写文件的详细功能和改动
 * @note
 */
final public class ThreadHelper {

    private volatile static ExecutorService singleThreadPool = null;

    /**
     * 在其他线程中执行
     *
     * @return
     */
    public static ExecutorService newThread() {
        if (singleThreadPool == null) {
            synchronized (ThreadHelper.class) {
                if (singleThreadPool == null) {
                    ThreadFactory namedThreadFactory = new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            return new Thread(r, String.valueOf(System.currentTimeMillis()));
                        }
                    };
                    singleThreadPool = new ThreadPoolExecutor(1, 5,
                            0L, TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return singleThreadPool;
    }

}
