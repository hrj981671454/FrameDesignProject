package com.eaju.imageloader.frescoLoader

import android.content.Context

import com.facebook.cache.common.CacheKey
import com.facebook.cache.common.SimpleCacheKey

import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter


/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:34
 */
class BrightnessFilterPostprocessor @JvmOverloads constructor(context: Context, private val brightness: Float = 0.0f) : GPUFilterPostprocessor(context, GPUImageBrightnessFilter()) {

    init {

        val filter = getFilter<GPUImageBrightnessFilter>()
        filter.setBrightness(brightness)
    }

    override fun getPostprocessorCacheKey(): CacheKey? {
        return SimpleCacheKey("brightness=" + brightness)
    }
}