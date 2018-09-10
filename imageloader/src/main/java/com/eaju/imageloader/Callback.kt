package com.eaju.imageloader
/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:34
 */
interface LoadImageCallback {
    fun onStart()

    fun onSuccess(result: Any?)

    fun onFail(error: Exception?)
}