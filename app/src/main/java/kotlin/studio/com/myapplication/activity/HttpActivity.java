package kotlin.studio.com.myapplication.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kotlin.studio.com.myapplication.R;
import kotlin.studio.com.myapplication.inject.ContentView;
import kotlin.studio.com.myapplication.inject.OnClick;
import kotlin.studio.com.myapplication.inject.ViewInject;
import kotlin.studio.com.myapplication.sql.bean.User;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-23 16:45
 */
@ContentView(R.layout.activity_http)
public class HttpActivity extends BaseActivity {

    @ViewInject(R.id.text_ioc)
    Button button;

    @ViewInject(R.id.tvResult)
    TextView textView;

    String url = "http://superapp.51eanj.com:82/eaju_app_service/super/eajAppUserRegister/login";


    @OnClick(R.id.text_ioc)
    public void onclick(View view) {

        User user = new User();
        user.setName("15110272604");
        user.setPassword("123456");
//        Volley.sendRequest(user, url);
    }

}
