package kotlin.studio.com.myapplication.imageload.request;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import kotlin.studio.com.myapplication.utils.LogsUtils;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:22
 */
public class RequestQueue {


    /**
     * 阻塞式队列
     */
    private BlockingQueue<BitmapRequest> mBitmapRequestQueue = new PriorityBlockingQueue<>();


    /**
     * 转发器数量
     */
    private int threadCount;


    /**
     * 转发器
     */
    private RequestDispatcher[] mDispatcher;


    private AtomicInteger i = new AtomicInteger(0);


    public RequestQueue(int threadCount) {
        this.threadCount = threadCount;
    }

    /**
     * 添加请求对象
     * @param bitmapRequest
     */
    public void addRequest(BitmapRequest bitmapRequest) {
        //判断请求队列有没有包含请求对象
        if (!mBitmapRequestQueue.contains(bitmapRequest)) {
            //给请求进行编号
            bitmapRequest.setSeriaNo(i.incrementAndGet());
            mBitmapRequestQueue.add(bitmapRequest);

        } else {
            LogsUtils.logI(RequestQueue.class, "请求已经存在 ：" + bitmapRequest.getSeriaNo());
        }
    }


    /**
     * 开始请求
     */
    public void start() {
        stop();
        startDispachers();
    }

    private void startDispachers() {
        mDispatcher = new RequestDispatcher[threadCount];
        for (int i = 0; i < threadCount; i++) {
            RequestDispatcher dispatcher = new RequestDispatcher(mBitmapRequestQueue);
            mDispatcher[i] =dispatcher;
            mDispatcher[i].start();
        }
    }

    /**
     * 停止
     */
    public void stop() {

    }

}
