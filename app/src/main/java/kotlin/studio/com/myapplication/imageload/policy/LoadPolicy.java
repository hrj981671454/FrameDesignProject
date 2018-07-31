package kotlin.studio.com.myapplication.imageload.policy;

import kotlin.studio.com.myapplication.imageload.request.BitmapRequest;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:25
 */
public interface LoadPolicy {

    /**
     * 优先级比较
     * @param bitmapRequest
     * @param bitmapRequest2
     * @return
     */
    int compareTo(BitmapRequest bitmapRequest, BitmapRequest bitmapRequest2);


}
