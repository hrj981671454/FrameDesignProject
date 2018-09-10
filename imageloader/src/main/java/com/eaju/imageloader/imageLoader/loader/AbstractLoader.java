package com.eaju.imageloader.imageLoader.loader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.eaju.imageloader.imageLoader.cache.BitmapCache;
import com.eaju.imageloader.imageLoader.config.DisplayConfig;
import com.eaju.imageloader.imageLoader.core.SimpleImageLoader;
import com.eaju.imageloader.imageLoader.request.BitmapRequest;


/**
 * Description: 抽象加载器
 * Copyright  : Copyright (c) 2018
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-08-25 11:56
 */
public abstract class AbstractLoader implements Loader {

    //缓存策略
    protected BitmapCache   mCache         = SimpleImageLoader.getInstance().getConfig().getBitmapCache();
    //显示配置
    private   DisplayConfig mDisplayConfig = SimpleImageLoader.getInstance().getConfig().getDisplayConfig();

    /**
     * 模板方法
     */
    @Override
    public void loadImage(BitmapRequest request) {
        //从缓存获取，缓存中没有再加载
        Bitmap bitmap = mCache.get(request);
        if (bitmap == null) {
            //加载前显示的图片
            showLoadingImage(request);

            //加载完成，再缓存
            //具体的加载方式，由子类决定
            bitmap = onLoad(request);
            cacheBitmap(request, bitmap);
        }

        //显示
        deliveryToUIThread(request, bitmap);
    }

    /**
     * 加载前显示的图片
     * @param request
     */
    protected void showLoadingImage(BitmapRequest request) {
        //指定了，显示配置
        if (hasLoadingPlaceHolder()) {
            final ImageView imageView = request.getImageView();
            if (imageView != null) {
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(mDisplayConfig.loadingImage);
                    }
                });
            }

        }
    }

    protected boolean hasLoadingPlaceHolder() {
        return (mDisplayConfig != null && mDisplayConfig.loadingImage > 0);
    }

    protected boolean hasFailedPlaceHolder() {
        return (mDisplayConfig != null && mDisplayConfig.failImage > 0);
    }

    /**
     * 交给主线程显示
     * @param request
     * @param bitmap
     */
    protected void deliveryToUIThread(final BitmapRequest request, final Bitmap bitmap) {
        ImageView imageView = request.getImageView();
        if (imageView != null) {
            imageView.post(new Runnable() {
                @Override
                public void run() {
                    updateImageView(request, bitmap);
                }
            });
        }

    }


    private void updateImageView(final BitmapRequest request, final Bitmap bitmap) {
        ImageView imageView = request.getImageView();
        //加载正常
        if (bitmap != null && imageView.getTag().equals(request.getImageUri())) {
            //            imageView.setImageBitmap(bitmap);
            imageView.setTag(null);
            Glide.with(imageView.getContext()).load(bitmap).into(imageView);
        }
        //有可能加载失败
        if (bitmap == null && hasFailedPlaceHolder()) {
            imageView.setTag(null);
            Glide.with(imageView.getContext()).load(mDisplayConfig.failImage).into(imageView);
        }
        //监听
        //回调
        if (request.imageListener != null) {
            request.imageListener.onComplete(imageView, bitmap, request.getImageUri());
        }
    }

    /**
     * 缓存
     * @param request
     * @param bitmap
     */
    private void cacheBitmap(BitmapRequest request, Bitmap bitmap) {
        if (request != null && bitmap != null) {
            synchronized (mCache) {
                mCache.put(request, bitmap);
            }
        }
    }

    /**
     * 具体的加载实现
     * @param request
     * @return
     */
    protected abstract Bitmap onLoad(BitmapRequest request);


}
