package com.eaju.imageloader.myLoader.request;

import android.util.Log;

import com.eaju.imageloader.myLoader.config.ImageLoaderConfig;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Description: 请求队列，所有图片加载的请求保存在该队列中
 * Copyright  : Copyright (c) 2018
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-08-24 10:49
 */
public class RequestQueue {
    //队列
    //多线程的数据共享
    //阻塞队列
    //生成效率和消费效率相差甚远，处理好兼顾效率和线程安全问题
    //concurrent出现了
    //优先级的阻塞队列
    //1.当队列中没有产品时，消费者被阻塞
    //2.使用优先级，优先级高的产品会被优先消费
    //前提：每个产品的都有一个编号（实例化出来的先后顺序）
    //优先级的确定，受产品编号的影响，但是由加载策略所决定

    private BlockingQueue<BitmapRequest> mRequestQueue = new PriorityBlockingQueue<BitmapRequest>();
    //一组转发器
    private RequestDispatcher[] mDispachers;

    //i++ ++i线程不安全
    //线程安全
    private AtomicInteger ai = new AtomicInteger(0);

    private ImageLoaderConfig imageLoaderConfig;

    /**
     * 构造函数
     * @param imageLoaderConfig
     */
    public RequestQueue(ImageLoaderConfig imageLoaderConfig) {
        this.imageLoaderConfig = imageLoaderConfig;
    }

    /**
     * 添加请求
     * @param request
     */
    public void addRequest(BitmapRequest request) {
        if (!mRequestQueue.contains(request)) {
            //给请求编号
            request.setSerialNO(ai.incrementAndGet());
            //设置缓存路径
            request.setImageCachePath(imageLoaderConfig.getImageCachePath());
            //设置缓存大小
            request.setImageCacheSize(imageLoaderConfig.getMaxCacheSize());
            //添加请求
            mRequestQueue.add(request);

            Log.d("jason", "添加请求" + request.getSerialNO());
        } else {
            Log.d("jason", "请求已经存在" + request.getSerialNO());
        }
    }


    /**
     * 开始
     */
    public void start() {
        //先停止，再启动
        stop();
        startDispatchers();
    }


    private void startDispatchers() {
        mDispachers = new RequestDispatcher[imageLoaderConfig.getThreadCount()];
        //初始化所有的转发器
        for (int i = 0; i < imageLoaderConfig.getThreadCount(); i++) {
            RequestDispatcher p = new RequestDispatcher(mRequestQueue);
            mDispachers[i] = p;
            //启动线程
            mDispachers[i].start();
        }
    }

    /**
     * 停止
     */
    public void stop() {
        if (mDispachers != null && mDispachers.length > 0) {
            for (int i = 0; i < mDispachers.length; i++) {
                //打断
                mDispachers[i].interrupt();
            }
        }
    }

}
