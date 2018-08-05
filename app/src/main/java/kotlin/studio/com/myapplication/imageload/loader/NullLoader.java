package kotlin.studio.com.myapplication.imageload.loader;

import android.graphics.Bitmap;

import kotlin.studio.com.myapplication.imageload.request.BitmapRequest;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018/8/5 22:08
 */
public class NullLoader extends AbstarctLoader {
    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        return null;
    }
}
