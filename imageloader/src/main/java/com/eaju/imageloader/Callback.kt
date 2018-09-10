package com.eaju.imageloader

interface LoadImageCallback {
    fun onStart()

    fun onSuccess(result: Any?)

    fun onFail(error: Exception?)
}