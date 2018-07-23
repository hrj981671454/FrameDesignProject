package kotlin.studio.com.myapplication.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

import kotlin.studio.com.myapplication.http.interfaces.IHttpListener;
import kotlin.studio.com.myapplication.http.interfaces.IHttpService;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-23 16:24
 */
public class JsonHttpService implements IHttpService {
    private IHttpListener httpListener;
    private HttpClient httpClient = new DefaultHttpClient();
    private HttpPost httpPost;
    private String   Url;
    //获取网络的回调
    private HttpResponseHandler httpResponseHandler = new HttpResponseHandler();

    private byte[] requestData;

    @Override
    public void setUrl(String url) {
        this.Url = url;
    }

    @Override
    public void excute() {
        httpPost = new HttpPost(Url);
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(requestData);
        httpPost.setEntity(byteArrayEntity);
        try {
            httpClient.execute(httpPost, httpResponseHandler);

        } catch (IOException e) {
            e.printStackTrace();
            httpListener.onFail();
        }
    }

    @Override
    public void setHttpListener(IHttpListener httpListener) {
        this.httpListener = httpListener;
    }

    @Override
    public void setRequest(byte[] request) {
        this.requestData = request;
    }


    private class HttpResponseHandler extends BasicResponseHandler {

        @Override
        public String handleResponse(HttpResponse response) throws IOException {

            //响应码
            int code = response.getStatusLine().getStatusCode();

            if (200 == code) {
                httpListener.onSuccess(response.getEntity());
            } else {
                httpListener.onFail();
            }

            return null;
        }
    }

}
