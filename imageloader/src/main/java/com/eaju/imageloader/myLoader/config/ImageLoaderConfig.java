package com.eaju.imageloader.myLoader.config;


import android.content.Context;
import android.text.TextUtils;

import com.eaju.imageloader.imageLoader.YAJImageLoader;
import com.eaju.imageloader.imageLoader.YAJImageLoaderManager;
import com.eaju.imageloader.myLoader.cache.BitmapCache;
import com.eaju.imageloader.myLoader.cache.NoCache;
import com.eaju.imageloader.myLoader.policy.LoadPolicy;
import com.eaju.imageloader.myLoader.policy.SerialPolicy;
import com.eaju.imageloader.myLoader.utils.CommonUtil;

/**
 * Description: 配置
 * Copyright  : Copyright (c) 2018
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-08-28 16:22
 */
public class ImageLoaderConfig {

    private Context context;
    //缓存策略
    private BitmapCache   bitmapCache   = new NoCache();
    //加载策略（honesty is the best policy）
    private LoadPolicy    loadPolicy    = new SerialPolicy();
    //线程个数
    //Java虚拟机可用的处理器个数
    private int           threadCount   = Runtime.getRuntime().availableProcessors();
    //图片记载的显示配置
    private DisplayConfig displayConfig = new DisplayConfig();

    //设置默认缓存地址
    private String imageCachePath;

    //设置缓存大小
    private long maxCacheSize = 20 * 1024 * 1024;

    //设置图片第三方加载框架
    private YAJImageLoader imageLoader;

    private ImageLoaderConfig(Context context) {
    }

    //生成器模式（不同的构建过程，生成不同表现形式的对象）
    //AlertDialog
    public static class Builder {
        private ImageLoaderConfig config;
        private Context           context;

        public Builder(Context context) {
            config = new ImageLoaderConfig(context);
            this.context = context;
        }

        /**
         * 设置缓存策略
         * @param bitmapCache
         * @return
         */
        public Builder setCachePolicy(BitmapCache bitmapCache) {
            config.bitmapCache = bitmapCache;
            return this;//链式编程
        }

        /**
         * 设置加载策略
         * @param loadPolicy
         * @return
         */
        public Builder setLoadPolicy(LoadPolicy loadPolicy) {
            config.loadPolicy = loadPolicy;
            return this;
        }

        /**
         * 设置线程个数
         * @param count
         * @return
         */
        public Builder setThreadCount(int count) {
            config.threadCount = count;
            return this;
        }

        /**
         * 图片加载过程中显示的图片
         * @param resId
         * @return
         */
        public Builder setLoadingPlaceHolder(int resId) {
            config.displayConfig.loadingImage = resId;
            return this;
        }


        /**
         * 图片加载失败显示的图片
         * @param resId
         * @return
         */
        public Builder setFailedPlaceHolder(int resId) {
            config.displayConfig.failImage = resId;
            return this;
        }

        /**
         * 设置自定义缓存地址
         * @return
         */
        public Builder setImageCachePath(String path) {
            if (TextUtils.isEmpty(path)) {
                config.imageCachePath = CommonUtil.getDiskCachePath(context);
            } else {
                config.imageCachePath = path;
            }
            return this;
        }

        /**
         * 设置自定义缓存大小
         * @return
         */
        public Builder setImageCacheSize(long size) {
            config.maxCacheSize = size;
            return this;
        }

        /**
         * 设置第三方图片加载框架
         * @return
         */
        public Builder setImageLoader(YAJImageLoader imageLoader) {
            YAJImageLoaderManager.Companion.initialize(imageLoader);
            config.imageLoader = imageLoader;
            return this;
        }

        public ImageLoaderConfig build() {
            return config;
        }
    }

    public int getThreadCount() {
        return threadCount;
    }

    public LoadPolicy getLoadPolicy() {
        return loadPolicy;
    }

    public BitmapCache getBitmapCache() {
        return bitmapCache;
    }

    public DisplayConfig getDisplayConfig() {
        return displayConfig;
    }

    public String getImageCachePath() {
        return imageCachePath;
    }

    public long getMaxCacheSize() {
        return maxCacheSize;
    }

    public YAJImageLoader getImageLoader() {
        return imageLoader;
    }
}
