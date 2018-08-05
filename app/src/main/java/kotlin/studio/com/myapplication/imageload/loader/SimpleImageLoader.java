package kotlin.studio.com.myapplication.imageload.loader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import kotlin.studio.com.myapplication.imageload.config.DisplayConfig;
import kotlin.studio.com.myapplication.imageload.config.ImageLoaderConfig;
import kotlin.studio.com.myapplication.imageload.request.BitmapRequest;
import kotlin.studio.com.myapplication.imageload.request.RequestQueue;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:22
 */
public class SimpleImageLoader {


    /**
     * 配置
     */
    private ImageLoaderConfig config;


    /**
     * 请求队列
     */
    private RequestQueue mRequestQueue;

    /**
     * 单例模式
     */
    private static volatile SimpleImageLoader instance;


    public static SimpleImageLoader getInstance(ImageLoaderConfig config) {
        if (null == instance) {
            synchronized (SimpleImageLoader.class) {
                if (null == instance) {
                    instance = new SimpleImageLoader(config);
                }
            }
        }
        return instance;
    }

    /**
     * 第二次获取单例
     * @return
     */
    public static SimpleImageLoader getInstance() {
        if (null == instance) {
            throw new UnsupportedOperationException("没有初始化");
        }
        return instance;
    }


    private SimpleImageLoader(ImageLoaderConfig config) {
        this.config = config;
        mRequestQueue = new RequestQueue(config.getThreadCount());
        //开启请求队列
    }


    /**
     * @param imageView
     *         图片
     * @param URI
     *         图片地址          t
     */
    public void diaplayImage(ImageView imageView, String URI) {
        diaplayImage(imageView, URI, null, null);

    }

    public void diaplayImage(ImageView imageView, String URI, DisplayConfig displayConfig, ImageListener imageListener) {
        //实例化一个对象
        BitmapRequest bitmapRequest = new BitmapRequest(imageView, URI, displayConfig, imageListener);
        //
        mRequestQueue.addRequest(bitmapRequest);

    }


    public interface ImageListener {

        void onComplete(ImageView imageView, Bitmap bitmap, String URI);
    }

    public ImageLoaderConfig getConfig() {
        return config;
    }
}
