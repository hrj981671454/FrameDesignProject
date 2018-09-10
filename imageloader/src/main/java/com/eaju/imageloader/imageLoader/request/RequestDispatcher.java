package com.eaju.imageloader.imageLoader.request;

import android.text.TextUtils;
import android.util.Log;

import com.eaju.imageloader.imageLoader.loader.Loader;
import com.eaju.imageloader.imageLoader.loader.LoaderManager;

import java.util.concurrent.BlockingQueue;


/**
 * Description: 请求转发线程，不断从请求队列中获取请求处理
 * Copyright  : Copyright (c) 2018
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-08-24 10:49
 */
public class RequestDispatcher extends Thread {


    //请求队列
    private BlockingQueue<BitmapRequest> mRequestQueue;

    public RequestDispatcher(BlockingQueue<BitmapRequest> mRequestQueue) {
        this.mRequestQueue = mRequestQueue;
    }

    @Override
    public void run() {
        //非阻塞状态，获取请求处理
        while (!isInterrupted()) {
            //从队列中获取优先级最高的请求进行处理
            try {
                BitmapRequest request = mRequestQueue.take();
                Log.d("jason", "---处理请求" + request.getSerialNO());
                //解析图片地址，获取对象的加载器
                String schema = parseSchema(request.getImageUri());
                Loader loader = LoaderManager.getInstance().getLoader(schema);
                loader.loadImage(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 解析图片地址，获取schema
     * @param imageUri
     * @return
     */
    private String parseSchema(String imageUri) {
        if (!TextUtils.isEmpty(imageUri)) {
            if (imageUri.contains("://")) {
                return imageUri.split("://")[0];
            } else {
                Log.e("jason", "图片地址schema异常！");
            }
        }
        return "";
    }

}
