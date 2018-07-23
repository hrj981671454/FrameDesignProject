package kotlin.studio.com.myapplication.http.interfaces;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-23 15:45
 */

import org.apache.http.HttpEntity;

/**
 * 处理结果接口
 */
public interface IHttpListener {
    /**
     * 成功
     * @param entity
     */
    void onSuccess(HttpEntity entity);


    /**
     * 失败
     */
    void onFail();
}
