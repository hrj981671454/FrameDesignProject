package kotlin.studio.com.myapplication.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.eaju.imageloader.myLoader.cache.DoubleCache;
import com.eaju.imageloader.myLoader.config.ImageLoaderConfig;
import com.eaju.imageloader.myLoader.core.SimpleImageLoader;
import com.eaju.imageloader.myLoader.policy.ReversePolicy;
import com.shuyu.gsygiideloader.YAJGlideImageLoader;

import kotlin.studio.com.myapplication.R;


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
    private final String dataBasePath   = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.eaju.main/database/";
    private final String logPath        = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.eaju.main/log/";
    private final String imageCachePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.eaju.main/image/";

    public String getImageCachePath() {
        return imageCachePath;
    }

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
        ImageLoaderConfig.Builder build = new ImageLoaderConfig.Builder(this);
        build.setThreadCount(3) //线程数量
                .setLoadPolicy(new ReversePolicy()) //加载策略
                .setCachePolicy(new DoubleCache(this)) //缓存策略
                .setLoadingPlaceHolder(R.mipmap.ic_launcher)
                .setFailedPlaceHolder(R.mipmap.ic_launcher)
                .setImageCachePath(getImageCachePath())
                .setImageCacheSize(50 * 1024 * 1024)
                .setImageLoader(new YAJGlideImageLoader(this));

        ImageLoaderConfig config = build.build();
        //初始化
        imageLoader = SimpleImageLoader.getInstance(config);
    }
}
