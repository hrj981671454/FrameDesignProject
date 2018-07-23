package kotlin.studio.com.myapplication.http;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import org.apache.http.HttpEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import kotlin.studio.com.myapplication.http.interfaces.IDataListener;
import kotlin.studio.com.myapplication.http.interfaces.IHttpListener;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-23 15:56
 */
public class JsonDealListener<M> implements IHttpListener {

    private Class<M> responese;

    private IDataListener dataListener;

    Handler handler = new Handler(Looper.getMainLooper());


    public JsonDealListener(Class<M> responese, IDataListener dataListener) {
        this.responese = responese;
        this.dataListener = dataListener;
    }

    @Override
    public void onSuccess(HttpEntity entity) {
        InputStream inputStream = null;
        try {
            inputStream = entity.getContent();

            String content = getContent(inputStream);
            final M m = (M) JSON.parseObject(content, responese);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    dataListener.onSuccess(m);
                }
            });
        } catch (Exception e) {
            try {
                inputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            dataListener.onFail();
        }
    }

    @Override
    public void onFail() {
        dataListener.onFail();
    }

    private String getContent(InputStream inputStream) {
        String content = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder sb = new StringBuilder();

            String line = null;

            try {

                while ((line = reader.readLine()) != null) {

                    sb.append(line + "\n");

                }

            } catch (IOException e) {

                System.out.println("Error=" + e.toString());
                dataListener.onFail();
            } finally {

                try {

                    inputStream.close();

                } catch (IOException e) {

                    System.out.println("Error=" + e.toString());

                }

            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            dataListener.onFail();
        }
        return content;
    }


}
