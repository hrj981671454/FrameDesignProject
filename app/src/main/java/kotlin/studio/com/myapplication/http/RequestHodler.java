package kotlin.studio.com.myapplication.http;

import kotlin.studio.com.myapplication.http.interfaces.IHttpListener;
import kotlin.studio.com.myapplication.http.interfaces.IHttpService;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-23 16:34
 */
public class RequestHodler<T> {

    /**
     * 执行下载类
     */
    private IHttpService  httpService;
    /**
     * 获取数据返回结果
     */
    private IHttpListener httpListener;
    /**
     * 请求参数对应的实体
     */
    private T             requestInfo;
    private String        url;


    public IHttpService getHttpService() {
        return httpService;
    }

    public void setHttpService(IHttpService httpService) {
        this.httpService = httpService;
    }

    public IHttpListener getHttpListener() {
        return httpListener;
    }

    public void setHttpListener(IHttpListener httpListener) {
        this.httpListener = httpListener;
    }

    public T getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(T requestInfo) {
        this.requestInfo = requestInfo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
