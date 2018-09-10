package kotlin.studio.com.myapplication.utils;

import android.util.Log;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-18 13:50
 */
public class LogsUtils {

    public static void logI(Class context, String logs) {
        String simpleName = context.getClass().getSimpleName();
        Log.i(simpleName, logs);
    }


    public static void logE(Class context, String logs) {
        String simpleName = context.getClass().getSimpleName();
        Log.e(simpleName, logs);
    }

    public static void logI(String tag, String logs) {
        Log.i(tag, logs);
    }

}
