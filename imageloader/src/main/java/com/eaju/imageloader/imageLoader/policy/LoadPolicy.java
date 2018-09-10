package com.eaju.imageloader.imageLoader.policy;


import com.eaju.imageloader.imageLoader.request.BitmapRequest;

/**
 * Description: 网络图片加载器
 * Copyright  : Copyright (c) 2018
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-08-24 10:49
 */
public interface LoadPolicy {

    /**
     * 两个BitmapRequest进行比较
     * @param request1
     * @param request2
     * @return 小于0，request1 < request2，大于0，request1 > request2，等于
     */
    public int compareTo(BitmapRequest request1, BitmapRequest request2);

}
