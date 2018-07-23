package kotlin.studio.com.myapplication.http;

import kotlin.studio.com.myapplication.http.interfaces.IDataListener;
import kotlin.studio.com.myapplication.http.interfaces.IHttpListener;
import kotlin.studio.com.myapplication.http.interfaces.IHttpService;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-23 15:44
 */
public class Volley {
    /**
     * @param <T>
     *         请求参数类型
     * @param <M>返回参数类型
     */
    public static <T, M> void sendRequest(T requestInfo,
                                          String url,
                                          Class<M> response,
                                          IDataListener dataListener) {

        RequestHodler<T> requestHodler = new RequestHodler<>();
        requestHodler.setUrl(url);
        IHttpService httpService = new JsonHttpService();
        IHttpListener httpListener = new JsonDealListener<>(response, dataListener);
        requestHodler.setHttpService(httpService);
        requestHodler.setHttpListener(httpListener);

        HttpTask<T> httpTask = new HttpTask<>(requestHodler);

    }
}
