package kotlin.studio.com.myapplication.http.interfaces;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-23 15:49
 */

/**
 * 获取网络
 */
public interface IHttpService {

    /**
     * 设置地址
     * @param url
     */

    void setUrl(String url);


    /**
     * 执行网络
     */
    void excute();


    /**
     * 设置处理接口
     */

    void setHttpListener(IHttpListener httpListener);


    /**
     * 设置请求参数
     */
    void setRequest(byte[] request);
}
