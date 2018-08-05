package kotlin.studio.com.myapplication.imageload.loader;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018/8/5 22:09
 */
public class LoaderManager {


    private Map<String, Loader> mLoaderMap = new HashMap<>();

    private static LoaderManager instance = new LoaderManager();


    public static LoaderManager getInstance() {
        return instance;
    }

    public LoaderManager() {
        register("http", new UrlLoader());
        register("https", new UrlLoader());
        register("file", new LocalLoader());
    }

    private void register(String http, Loader urlLoader) {
        mLoaderMap.put(http, urlLoader);
    }


    public Loader getLoader(String schema) {
        if (mLoaderMap.containsKey(schema)) {
            return mLoaderMap.get(schema);
        }
        return new NullLoader();
    }
}
