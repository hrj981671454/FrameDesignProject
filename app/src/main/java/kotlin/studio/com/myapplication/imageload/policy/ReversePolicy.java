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
 * 倒序
 */
public class ReversePolicy implements LoadPolicy {
    @Override
    public int compareTo(BitmapRequest bitmapRequest, BitmapRequest bitmapRequest2) {
        return bitmapRequest2.getSeriaNo() - bitmapRequest.getSeriaNo();
    }
}
