package kotlin.studio.com.myapplication.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import kotlin.studio.com.myapplication.R;
import kotlin.studio.com.myapplication.imageload.cache.DoubleCache;
import kotlin.studio.com.myapplication.imageload.config.ImageLoaderConfig;
import kotlin.studio.com.myapplication.imageload.core.SimpleImageLoader;
import kotlin.studio.com.myapplication.imageload.policy.ReversePolicy;

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

    public SimpleImageLoader getImageLoader() {
        return imageLoader;
    }

    private SimpleImageLoader imageLoader;

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
        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfig.Builder build = new ImageLoaderConfig.Builder();
        build.setThreadCount(3) //线程数量
                .setLoadPolicy(new ReversePolicy()) //加载策略
                .setCachePolicy(new DoubleCache(this)) //缓存策略
                .setLoadingPlaceHolder(R.mipmap.ic_launcher)
                .setFailedPlaceHolder(R.mipmap.ic_launcher);

        ImageLoaderConfig config = build.build();
        //初始化
        imageLoader = SimpleImageLoader.getInstance(config);
    }
}
