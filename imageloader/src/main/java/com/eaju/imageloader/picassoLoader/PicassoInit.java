package com.eaju.imageloader.picassoLoader;

import android.content.Context;

import com.eaju.imageloader.myLoader.core.SimpleImageLoader;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-09-12 15:47
 */
public class PicassoInit {

    //单例对象
    private static PicassoInit mInstance;

    private Context context;

    private PicassoInit(Context context) {
        this.context = context;
    }


    public static PicassoInit getInstance(Context context) {
        if (mInstance == null) {
            synchronized (SimpleImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new PicassoInit(context);
                }
            }
        }
        return mInstance;
    }

    private Picasso init() {
        OkHttp3Downloader downloadCache = new OkHttp3Downloader(new File(SimpleImageLoader.getInstance().getConfig().getImageCachePath()),
                SimpleImageLoader.getInstance().getConfig().getMaxCacheSize());
        Picasso mPicassoLoader = new Picasso
                .Builder(context)
                .downloader(downloadCache)
                .build();
        return mPicassoLoader;
    }

    public Picasso initPicasso() {
        Picasso picasso = init();
        if (null == picasso) {
            return init();
        } else {
            return picasso;
        }
    }
}
