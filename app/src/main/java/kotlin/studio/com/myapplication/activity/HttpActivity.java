package kotlin.studio.com.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kotlin.studio.com.myapplication.R;
import kotlin.studio.com.myapplication.inject.ContentView;
import kotlin.studio.com.myapplication.inject.OnClick;
import kotlin.studio.com.myapplication.inject.ViewInject;
import kotlin.studio.com.myapplication.sql.bean.Download;
import kotlin.studio.com.myapplication.sql.bean.User;
import kotlin.studio.com.myapplication.sql.dao.DownloadDao;
import kotlin.studio.com.myapplication.sql.dao.UserDao;
import kotlin.studio.com.myapplication.sql.factory.DaoManagerFactory;
import kotlin.studio.com.myapplication.sql.update.UpdateManager;

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


    @ViewInject(R.id.text_ioc2)
    Button button2;

    @ViewInject(R.id.tvResult)
    TextView textView;

    String url = "http://superapp.51eanj.com:82/eaju_app_service/super/eajAppUserRegister/login";
    private UserDao dataHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataHelper = DaoManagerFactory.getInstance().getDataHelper(UserDao.class, User.class);
    }

    @OnClick(R.id.text_ioc)
    public void onclick(View view) {
        final User user = new User();
        user.setPhoneNumber("15296711325");
        user.setPassword("123456");
        user.setLogin(true);
        user.setToken("1234787965465");
        user.setCreateTime(System.currentTimeMillis());
        user.setOutLoginTime(System.currentTimeMillis() + 1000 * 60 * 60);
        user.setLoginTime(System.currentTimeMillis());

        dataHelper.insert(user);

        UpdateManager updateManager = new UpdateManager();
        updateManager.checkThisVersionTable(this);

      /*  Volley.sendRequest(user, url, LoginBean.class, new IDataListener<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                if (loginBean.getReturnCode() == 200) {
                    textView.setText("登录信息：" + loginBean.getInfo());
                } else {
                    textView.setText("登录信息：" + loginBean.getInfo());
                }

            }

            @Override
            public void onFail() {
                textView.setText("出错了");
            }
        });*/
    }


    @OnClick(R.id.text_ioc2)
    public void onclick2(View view) {
        DownloadDao userHelper = DaoManagerFactory.getInstance().getUserHelper(DownloadDao.class, Download.class);
        for (int i = 0; i < 10; i++) {
            Download download = new Download();
            download.setName("何仁杰" + i);
            download.setPassword("123456" + i);
            download.setAge(i);
            userHelper.insert(download);
        }
    }
}
