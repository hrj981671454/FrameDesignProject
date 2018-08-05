package kotlin.studio.com.myapplication.imageload.loader;

import kotlin.studio.com.myapplication.imageload.request.BitmapRequest;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:23
 */
public interface Loader {

    /**
     * 加载图片
     * @param request
     */
    void loadImage(BitmapRequest request);

}
