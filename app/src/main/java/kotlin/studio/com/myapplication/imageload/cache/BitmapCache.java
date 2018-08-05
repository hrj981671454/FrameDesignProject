package kotlin.studio.com.myapplication.imageload.cache;

import android.graphics.Bitmap;

import kotlin.studio.com.myapplication.imageload.request.BitmapRequest;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:27
 */
public interface BitmapCache {

    /**
     * 缓存图片
     * @param request
     * @param bitmap
     */
    void put(BitmapRequest request, Bitmap bitmap);

    /**
     * 通过请求去Bitmap
     * @param request
     * @return
     */
    Bitmap get(BitmapRequest request);


    void remove(BitmapRequest request);
}
