package com.eaju.imageloader.imageLoader.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.eaju.imageloader.imageLoader.request.BitmapRequest;


/**
 * Description: 内存缓存
 * Copyright  : Copyright (c) 2018
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-08-30 14:43
 */

public class MemoryCache implements BitmapCache {

    private LruCache<String, Bitmap> mLruCache;

    public MemoryCache() {
        //缓存的最大值（可用内存的1/8）
        int maxSize = (int) Runtime.getRuntime().freeMemory() / 1024 / 8;
        mLruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //一个Bitmap的大小
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        mLruCache.put(request.getImageUriMD5(), bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        return mLruCache.get(request.getImageUriMD5());
    }

    @Override
    public void remove(BitmapRequest request) {
        mLruCache.remove(request.getImageUriMD5());
    }

}
