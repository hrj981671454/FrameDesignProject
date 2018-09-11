package com.eaju.imageloader

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import com.eaju.imageloader.frescoLoader.BrightnessFilterPostprocessor
import com.eaju.imageloader.frescoLoader.YAJFrescoImageLoader
import com.eaju.imageloader.glideLoader.BlurTransformation
import com.eaju.imageloader.glideLoader.YAJGlideImageLoader
import com.eaju.imageloader.imageLoader.YAJImageLoader
import com.eaju.imageloader.imageLoader.YAJImageLoaderManager
import com.eaju.imageloader.imageLoader.YAJLoadOption
import com.eaju.imageloader.picassoLoader.ColorFilterTransformations
import com.eaju.imageloader.picassoLoader.YAJPicassoImageLoader
import kotlin.properties.Delegates

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-09-07 15:29
 */
class ImageLoader {


    companion object {
        var mInstance: ImageLoader? = null
            private set

        /**
         * 获取单例方法
         * 第一次调用
         * @param config
         * @return
         */
        fun getInstance(): ImageLoader? {
            if (mInstance == null) {
                synchronized(ImageLoader::class.java) {
                    if (mInstance == null) {
                        mInstance = ImageLoader()
                    }
                }
            }
            return mInstance
        }
    }

    fun loadImage(context: Context, url: String, target: Any?) {
        val option = setOption(context, url, 0, 0)
        YAJImageLoaderManager.Companion.sInstance
                .imageLoader()
                .loadImage(option, target, null, null);
    }

    fun loadImage(context: Context, url: String, target: Any?, callback: LoadImageCallback) {
        val option = setOption(context, url, 0, 0)
        YAJImageLoaderManager.Companion.sInstance
                .imageLoader()
                .loadImage(option, target, object : YAJImageLoader.Callback {
                    override fun onStart() {
                        callback.onStart()
                    }

                    override fun onSuccess(result: Any?) {
                        callback.onSuccess(result)
                    }

                    override fun onFail(error: Exception?) {
                        callback.onFail(error)
                    }
                }, null);
    }

    fun loadImage(context: Context, url: String, DefaultImg: Int, ErrorImg: Int, target: Any?, callback: LoadImageCallback) {
        val option = setOption(context, url, DefaultImg, ErrorImg)
        YAJImageLoaderManager.Companion.sInstance
                .imageLoader()
                .loadImage(option, target, object : YAJImageLoader.Callback {
                    override fun onStart() {
                        callback.onStart()
                    }

                    override fun onSuccess(result: Any?) {
                        callback.onSuccess(result)
                    }

                    override fun onFail(error: Exception?) {
                        callback.onFail(error)
                    }
                }, null);
    }

    fun loadImage(context: Context, url: String, DefaultImg: Int, ErrorImg: Int,
                  isCircle: Boolean?, isPlayGif: Boolean?, size: Point?, target: Any?, callback: LoadImageCallback) {
        val option = setAllOption(context, url, DefaultImg, ErrorImg, isCircle, isPlayGif, size)
        YAJImageLoaderManager.Companion.sInstance
                .imageLoader()
                .loadImage(option, target, object : YAJImageLoader.Callback {
                    override fun onStart() {
                        callback.onStart()
                    }

                    override fun onSuccess(result: Any?) {
                        callback.onSuccess(result)
                    }

                    override fun onFail(error: Exception?) {
                        callback.onFail(error)
                    }
                }, null);
    }

    private fun setOption(context: Context, url: String, DefaultImg: Int, ErrorImg: Int): YAJLoadOption {
        val allOption = setAllOption(context, url, DefaultImg, ErrorImg, false, false, null)
        return allOption
    }

    private fun setAllOption(context: Context, url: String, DefaultImg: Int, ErrorImg: Int,
                             isCircle: Boolean?, isPlayGif: Boolean?, size: Point?): YAJLoadOption {
        val transformations = YAJLoadOption().setUri(url)
                .setDefaultImg(DefaultImg)
                .setErrorImg(ErrorImg).setSize(size).setPlayGif(isPlayGif!!)
                .setCircle(isCircle!!)
        return transformations
    }
}
