package com.eaju.imageloader.glideLoader;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;
import com.eaju.imageloader.myLoader.config.ImageLoaderConfig;
import com.eaju.imageloader.myLoader.core.SimpleImageLoader;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-09-11 15:55
 */
@com.bumptech.glide.annotation.GlideModule
public class MyGlideModule implements GlideModule {


    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        ImageLoaderConfig config = SimpleImageLoader.getInstance().getConfig();

        builder.setDiskCache(new DiskLruCacheFactory(config.getImageCachePath(), config.getMaxCacheSize()));
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {

    }
}
