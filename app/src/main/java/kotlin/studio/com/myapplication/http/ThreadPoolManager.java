package kotlin.studio.com.myapplication.http;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import kotlin.studio.com.myapplication.utils.LogsUtils;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018/7/23 22:24
 */
public class ThreadPoolManager {
    private static ThreadPoolManager instance = new ThreadPoolManager();

    private LinkedBlockingQueue<Future<?>>     taskQuene = new LinkedBlockingQueue<>();

    private ThreadPoolExecutor threadPoolExecutor;

    public static ThreadPoolManager getInstance() {
        return instance;
    }

    private ThreadPoolManager() {
        threadPoolExecutor = new ThreadPoolExecutor(4,
                10,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4),
                handler);
        threadPoolExecutor.execute(runnable);
    }

    public <T> void excute(FutureTask<T> futureTask) throws InterruptedException {
        taskQuene.put(futureTask);
    }


    private Runnable                 runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                FutureTask futureTask = null;
                try {
                    futureTask = (FutureTask) taskQuene.take();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LogsUtils.logI(ThreadPoolManager.class, "等待队列     " + taskQuene.size());
                if (null != futureTask) {
                    threadPoolExecutor.execute(futureTask);
                }

            }

        }
    };
    private RejectedExecutionHandler handler  = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                taskQuene.put(new FutureTask<Object>(r, null) {

                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
