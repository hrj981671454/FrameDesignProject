package com.shuyu.gsygiideloader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.disklrucache.DiskLruCache
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper
import com.bumptech.glide.load.engine.cache.MemoryCache
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.EmptySignature
import com.shuyu.gsyimageloader.YAJImageConst
import com.shuyu.gsyimageloader.YAJImageLoader
import com.shuyu.gsyimageloader.YAJLoadOption
import com.shuyu.gsyimageloader.YAJReflectionHelpers
import java.io.File
import java.lang.IllegalStateException


/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:34
 */
class YAJGlideImageLoader(private val context: Context) : YAJImageLoader {


    override fun loadImage(loadOption: YAJLoadOption, target: Any?, callback: YAJImageLoader.Callback?, extendOption: YAJImageLoader.ExtendedOptions?) {
        if (target !is ImageView) {
            throw IllegalStateException("target must be ImageView")
        }
        loadImage(loadOption, extendOption)
                .load(loadOption.mUri)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target:com.bumptech.glide.request.target.Target<Drawable>, isFirstResource: Boolean): Boolean {
                        callback?.let {
                            it.onFail(e)
                        }
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any, target: com.bumptech.glide.request.target.Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        callback?.let {
                            it.onSuccess(resource)
                        }
                        return false
                    }
                })
                .into(target)
    }

    override fun clearCache(type: Int) {
        when (type) {
            YAJImageConst.CLEAR_ALL_CACHE -> {
                Glide.get(context.applicationContext).clearMemory()
                Glide.get(context.applicationContext).clearDiskCache()
            }
            YAJImageConst.CLEAR_MEMORY_CACHE ->
                Glide.get(context.applicationContext).clearMemory()
            YAJImageConst.CLEAR_DISK_CACHE ->
                Glide.get(context.applicationContext).clearDiskCache()
        }
    }

    override fun clearCacheKey(type: Int, loadOption: YAJLoadOption) {
        val deleteDisk = {
            val diskCache = DiskLruCacheWrapper.create(Glide.getPhotoCacheDir(context), (250 * 1024 * 1024).toLong())
            val key = YAJGlideCacheKey(loadOption.mUri as String, EmptySignature.obtain())
            diskCache.delete(key)
        }
        val deleteMemory = {
            try {
                val key = YAJGlideCacheKey(loadOption.mUri as String, EmptySignature.obtain());
                val cache = YAJReflectionHelpers.getField<MemoryCache>(Glide.get(context.applicationContext), "memoryCache")
                cache.remove(key)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        when (type) {
            YAJImageConst.CLEAR_ALL_CACHE -> {
                deleteMemory.invoke()
                deleteDisk.invoke()
            }
            YAJImageConst.CLEAR_MEMORY_CACHE -> {
                deleteMemory()
            }
            YAJImageConst.CLEAR_DISK_CACHE -> {
                deleteDisk.invoke()
            }

        }
    }

    override fun getLocalCache(loadOption: YAJLoadOption, extendOption: YAJImageLoader.ExtendedOptions?): File? {
        val future = loadImage(loadOption, extendOption)
                .asFile().load(loadOption.mUri)
        return future.submit().get()
    }


    override fun isCache(loadOption: YAJLoadOption, extendOption: YAJImageLoader.ExtendedOptions?): Boolean {
        // 寻找缓存图片
        val file = DiskLruCacheWrapper.create(Glide.getPhotoCacheDir(context), (250 * 1024 * 1024).toLong())
                .get(YAJGlideCacheKey(loadOption.mUri as String, EmptySignature.obtain()))
        return file != null
    }

    override fun getLocalCacheBitmap(loadOption: YAJLoadOption, extendOption: YAJImageLoader.ExtendedOptions?): Bitmap? {
        val future = loadImage(loadOption, extendOption)
                .asBitmap().load(loadOption.mUri)
        return future.submit().get()
    }


    override fun getCacheSize(): Long? {
        val cache =  DiskLruCacheWrapper.create(Glide.getPhotoCacheDir(context), (250 * 1024 * 1024).toLong())
        val diskLruCache = YAJReflectionHelpers.getField<DiskLruCache>(cache, "diskLruCache")
        return diskLruCache.size()
    }

    override fun downloadOnly(loadOption: YAJLoadOption, callback: YAJImageLoader.Callback?, extendOption: YAJImageLoader.ExtendedOptions?) {
        loadImage(loadOption, extendOption).downloadOnly().load(loadOption.mUri).into(YAJImageDownLoadTarget(callback))
    }

    private fun loadImage(loadOption: YAJLoadOption, extendOption: YAJImageLoader.ExtendedOptions?): RequestManager {
        return Glide.with(context.applicationContext)
                .setDefaultRequestOptions(getOption(loadOption, extendOption))
    }

    @SuppressWarnings("CheckResult")
    private fun getOption(loadOption: YAJLoadOption, extendOption: YAJImageLoader.ExtendedOptions?): RequestOptions {
        val requestOptions = RequestOptions()
        if (loadOption.mErrorImg > 0) {
            requestOptions.error(loadOption.mErrorImg)
        }
        if (loadOption.mDefaultImg > 0) {
            requestOptions.placeholder(loadOption.mDefaultImg)
        }
        if (loadOption.isCircle) {
            requestOptions.circleCrop()
        }
        loadOption.mSize?.let {
            requestOptions.override(it.x, it.y)
        }
        if(loadOption.mTransformations.isNotEmpty()) {
            requestOptions.transform(MultiTransformation(loadOption.mTransformations as ArrayList<Transformation<Bitmap>>))
        }
        extendOption?.let {
            extendOption.onOptionsInit(requestOptions)
        }
        return requestOptions
    }

}

