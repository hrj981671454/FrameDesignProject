package com.eaju.imageloader.myLoader.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.widget.ImageView;

import com.eaju.imageloader.ImageLoader;
import com.eaju.imageloader.LoadImageCallback;
import com.eaju.imageloader.myLoader.config.DisplayConfig;
import com.eaju.imageloader.myLoader.config.ImageLoaderConfig;
import com.eaju.imageloader.myLoader.request.BitmapRequest;
import com.eaju.imageloader.myLoader.request.RequestQueue;
import com.eaju.imageloader.picassoLoader.YAJPicassoImageLoader;
import com.shuyu.gsyfrescoimageloader.YAJFrescoImageLoader;
import com.shuyu.gsygiideloader.YAJGlideImageLoader;
import com.shuyu.gsyimageloader.YAJImageLoader;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Description: 配置
 * Copyright  : Copyright (c) 2018
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-08-28 16:22
 */

public class SimpleImageLoader {
    //配置
    private                 ImageLoaderConfig config;
    //请求队列
    private                 RequestQueue      mRequestQueue;
    //单例对象
    private static volatile SimpleImageLoader mInstance;

    private SimpleImageLoader() {

    }

    private SimpleImageLoader(ImageLoaderConfig imageLoaderConfig) {
        this.config = imageLoaderConfig;
        mRequestQueue = new RequestQueue(imageLoaderConfig);
        //开启请求队列
        mRequestQueue.start();
    }

    /**
     * 获取单例方法
     * 第一次调用
     * @param config
     * @return
     */
    public static SimpleImageLoader getInstance(ImageLoaderConfig config) {
        if (mInstance == null) {
            synchronized (SimpleImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new SimpleImageLoader(config);
                }
            }
        }
        return mInstance;
    }

    /**
     * 第二次获取单例
     * @return
     */
    public static SimpleImageLoader getInstance() {
        if (mInstance == null) {
            throw new UnsupportedOperationException("没有初始化");
        }
        return mInstance;
    }

    /**
     * 暴露获取图片
     * @param imageView
     * @param uri
     *         http:   file 开头
     */
    public void loadImage(ImageView imageView, String uri) {
        loadImage(imageView, uri, null, null);
    }


    /**
     * 重载
     * @param imageView
     * @param uri
     * @param displayConfig
     * @param imageListener
     */
    public void loadImage(ImageView imageView, String uri
            , DisplayConfig displayConfig, ImageListener imageListener) {
        YAJImageLoader imageLoader = getConfig().getImageLoader();
        if (null == imageLoader) {
            //实例化一个请求
            BitmapRequest bitmapRequest = new BitmapRequest(imageView, uri, displayConfig, imageListener);
            //添加到队列里面
            mRequestQueue.addRequest(bitmapRequest);
        } else {
            if (imageLoader instanceof YAJGlideImageLoader) {
                loadImage(imageView, uri, displayConfig, false, false, null, imageListener);
            } else if (imageLoader instanceof YAJFrescoImageLoader) {
                loadImage(imageView, uri, displayConfig, false, false, null, imageListener);
            } else if (imageLoader instanceof YAJPicassoImageLoader) {
                loadImage(imageView, uri, displayConfig, false, false, null, imageListener);
            } else {
                throw new NullPointerException("Please set up ImageLoader");
            }
        }

    }

    public void loadImage(final ImageView imageView, final String URL,
                          DisplayConfig displayConfig, Boolean isCircle,
                          Boolean isPlayGif, Point point,
                          final ImageListener imageListener) {
        ImageLoader
                .Companion
                .getInstance()
                .loadImage(imageView.getContext(),
                        URL,
                        displayConfig.loadingImage,
                        displayConfig.failImage,
                        isCircle,
                        isPlayGif,
                        point,
                        imageView,
                        new LoadImageCallback() {
                            @Override
                            public void onStart() {
                            }

                            @Override
                            public void onSuccess(@Nullable Object result) {
                                imageListener.onComplete(imageView, result, URL);
                            }

                            @Override
                            public void onFail(@Nullable Exception error) {
                                imageListener.onFail(error);
                            }
                        });
    }

    public static interface ImageListener {
        /**
         * @param imageView
         * @param bitmap
         * @param uri
         */
        void onComplete(ImageView imageView, Object bitmap, String uri);

        void onFail(@Nullable Exception error);
    }

    /**
     * 拿到全局配置
     * @return
     */
    public ImageLoaderConfig getConfig() {
        return config;
    }


    /**
     * 根据 路径 得到 file 得到 bitmap
     * @param filePath
     * @return
     * @throws IOException
     */
    private Bitmap decodeFile(String filePath) throws IOException {
        Bitmap b = null;
        int IMAGE_MAX_SIZE = 600;

        File f = new File(filePath);
        if (f == null) {
            return null;
        }
        //Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;

        FileInputStream fis = new FileInputStream(f);
        BitmapFactory.decodeStream(fis, null, o);
        fis.close();

        int scale = 1;
        if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
            scale = (int) Math.pow(2, (int) Math.round(Math.log(IMAGE_MAX_SIZE / (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
        }

        //Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        fis = new FileInputStream(f);
        b = BitmapFactory.decodeStream(fis, null, o2);
        fis.close();
        return b;
    }
}
