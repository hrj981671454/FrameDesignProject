package kotlin.studio.com.myapplication.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-19 14:12
 */
public class App extends Application {
    private static App     instance;
    private static Context context;
    private final String dataBasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.eaju.main/database/";
    private final String logPath      = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.eaju.main/log/";

    public String getLogPath() {
        return logPath;
    }

    public String getDataBasePath() {
        return dataBasePath;
    }

    public static Context getContext() {
        return context;
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        instance = this;
    }
}
