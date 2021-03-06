package com.eaju.imageloader.frescoLoader

import android.content.Context
import android.graphics.Bitmap

import com.facebook.imagepipeline.request.BasePostprocessor

import jp.co.cyberagent.android.gpuimage.GPUImage
import jp.co.cyberagent.android.gpuimage.GPUImageFilter

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:34
 */
abstract class GPUFilterPostprocessor(context: Context, private val filter: GPUImageFilter) : BasePostprocessor() {

    private val context: Context = context.applicationContext

    override fun process(dest: Bitmap, source: Bitmap) {
        val gpuImage = GPUImage(context)
        gpuImage.setImage(source)
        gpuImage.setFilter(filter)
        val bitmap = gpuImage.bitmapWithFilterApplied

        super.process(dest, bitmap)
    }

    override fun getName(): String {
        return javaClass.simpleName
    }

    fun <T> getFilter(): T {
        return filter as T
    }
}
