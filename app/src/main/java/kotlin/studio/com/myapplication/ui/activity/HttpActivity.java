package kotlin.studio.com.myapplication.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kotlin.studio.com.myapplication.R;
import kotlin.studio.com.myapplication.inject.ContentView;
import kotlin.studio.com.myapplication.inject.OnClick;
import kotlin.studio.com.myapplication.inject.ViewInject;
import kotlin.studio.com.myapplication.sql.bean.LoginInfo;
import kotlin.studio.com.myapplication.sql.bean.User;
import kotlin.studio.com.myapplication.sql.dao.LoginInfoDao;
import kotlin.studio.com.myapplication.sql.dao.UserDao;
import kotlin.studio.com.myapplication.sql.factory.DaoManagerFactory;
import kotlin.studio.com.myapplication.sql.update.UpdateUtil;

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

    @ViewInject(R.id.text_ioc3)
    Button button3;

    @ViewInject(R.id.tvResult)
    TextView textView;

    //    String url = "http://superapp.51eanj.com:82/eaju_app_service/super/eajAppUserRegister/login";
    private UserDao    dataHelper;
    private UpdateUtil updateManager;
    int i = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataHelper = DaoManagerFactory.getInstance().getDataHelper(UserDao.class, User.class);
        updateManager = new UpdateUtil();
    }

    @OnClick(R.id.text_ioc)
    public void onclick(View view) {
        final User user = new User();
        user.setPhoneNumber("888888" + i++);
        user.setPassword("123456");
        user.setLogin(true);
        user.setToken("1234787965465");
        user.setCreateTime(System.currentTimeMillis());
        user.setOutLoginTime(System.currentTimeMillis() + 1000 * 60 * 60);
        user.setLoginTime(System.currentTimeMillis());

        dataHelper.insert(user);


       LoginInfoDao userHelper = DaoManagerFactory.getInstance().getUserHelper(LoginInfoDao.class, LoginInfo.class);
        for (int i = 0; i < 10; i++) {
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setName("何仁杰" + i);
            loginInfo.setPassword("123456" + i);
            loginInfo.setAge(i);
            userHelper.insert(loginInfo);
        }
    }

    @OnClick(R.id.text_ioc3)
    public void onClick3(View view) {
        updateManager.saveVersionInfo(this, "2.0");

        updateManager.checkThisVersionTable(this);
        
        updateManager.startUpdateDb(this);

    }
}
