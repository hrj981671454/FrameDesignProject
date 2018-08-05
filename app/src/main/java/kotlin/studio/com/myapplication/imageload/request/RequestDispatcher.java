package kotlin.studio.com.myapplication.imageload.request;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:23
 */

import java.util.concurrent.BlockingQueue;

/**
 * 请求转发线程
 */
public class RequestDispatcher extends Thread {
    /**
     * 请求队列
     */
    private BlockingQueue<BitmapRequest> mRequestsQueue;


    public RequestDispatcher(BlockingQueue<BitmapRequest> mRequestsQueue) {
        this.mRequestsQueue = mRequestsQueue;
    }


    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                BitmapRequest request = mRequestsQueue.take();
                /**
                 * 处理请求对象
                 */
                String schema = pareSchme(request.getImageUrl());

//                Loder
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String pareSchme(String imageUrl) {
        if (imageUrl.contains("://")) {
            return imageUrl.split("://")[0];
        } else {
            //扩展
        }
        return null;
    }
}
