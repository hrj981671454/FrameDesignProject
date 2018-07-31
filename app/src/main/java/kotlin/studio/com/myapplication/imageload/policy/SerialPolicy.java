package kotlin.studio.com.myapplication.imageload.policy;

import kotlin.studio.com.myapplication.imageload.request.BitmapRequest;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:26
 */

/**
 * 正序
 */
public class SerialPolicy implements LoadPolicy {
    @Override
    public int compareTo(BitmapRequest bitmapRequest, BitmapRequest bitmapRequest2) {
        return bitmapRequest.getSeriaNo() - bitmapRequest2.getSeriaNo();
    }
}
