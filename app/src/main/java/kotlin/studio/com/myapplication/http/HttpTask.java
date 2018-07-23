package kotlin.studio.com.myapplication.http;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

import kotlin.studio.com.myapplication.http.interfaces.IHttpService;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-23 16:32
 */
public class HttpTask<T> implements Runnable {
    private IHttpService httpService;

    public HttpTask(RequestHodler requestHodler) {
        httpService = requestHodler.getHttpService();
        httpService.setHttpListener(requestHodler.getHttpListener());
        httpService.setUrl(requestHodler.getUrl());

        T requestInfo = (T) requestHodler.getRequestInfo();

        String s = JSON.toJSONString(requestInfo);
        try {
            httpService.setRequest(s.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        httpService.excute();
    }
}
