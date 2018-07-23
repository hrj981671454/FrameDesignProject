package kotlin.studio.com.myapplication.http.interfaces;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-23 15:53
 */
public interface IDataListener<M> {
    void onSuccess(M m);

    void onFail();
}
