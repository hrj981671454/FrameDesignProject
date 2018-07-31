package kotlin.studio.com.myapplication.imageload.config;

import kotlin.studio.com.myapplication.imageload.cache.BitmapCache;
import kotlin.studio.com.myapplication.imageload.policy.LoadPolicy;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:21
 */
public class ImageLoaderConfig {
    /**
     * 缓存策略
     */
    private BitmapCache bitmapCache;


    /**
     * 加载策略
     */
    private LoadPolicy loadPolicy;


    /**
     * 默认线程数，根据Cpu动态获取
     */
    private int threadCount = Runtime.getRuntime().availableProcessors();


    private DisplayConfig displayConfig;


    private ImageLoaderConfig() {

    }

    public BitmapCache getBitmapCache() {
        return bitmapCache;
    }

    public void setBitmapCache(BitmapCache bitmapCache) {
        this.bitmapCache = bitmapCache;
    }

    public LoadPolicy getLoadPolicy() {
        return loadPolicy;
    }

    public void setLoadPolicy(LoadPolicy loadPolicy) {
        this.loadPolicy = loadPolicy;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public DisplayConfig getDisplayConfig() {
        return displayConfig;
    }

    public void setDisplayConfig(DisplayConfig displayConfig) {
        this.displayConfig = displayConfig;
    }


    /**
     * 建造者开发模式
     */
    public static class Builder {

        private ImageLoaderConfig config;

        public Builder() {
            config = new ImageLoaderConfig();
        }


        /**
         * 设置缓存策略
         * @param bitmapCache
         * @return
         */
        public Builder setCachePolicy(BitmapCache bitmapCache) {
            config.bitmapCache = bitmapCache;
            return this;
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
         * 设置线程数
         * @param count
         * @return
         */
        public Builder setThreadCount(int count) {
            config.threadCount = count;
            return this;
        }


        /**
         * 设置加载过程中的图片
         * @param resID
         * @return
         */
        public Builder setLoadingImage(int resID) {
            config.displayConfig.loadingImage = resID;
            return this;
        }


        public Builder setFaildImage(int resID) {
            config.displayConfig.failImage = resID;
            return this;
        }


        public ImageLoaderConfig build() {
            return config;
        }
    }

}
