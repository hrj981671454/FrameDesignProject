package com.shuyu.gsyfrescoimageloader

import android.content.Context
import android.graphics.Bitmap
import com.facebook.binaryresource.FileBinaryResource
import com.facebook.common.executors.CallerThreadExecutor
import com.facebook.datasource.BaseDataSubscriber
import com.facebook.datasource.DataSource
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.core.ImagePipelineFactory
import com.facebook.imagepipeline.image.CloseableBitmap
import com.facebook.imagepipeline.request.ImageRequest
import com.shuyu.gsyimageloader.YAJImageConst
import com.shuyu.gsyimageloader.YAJImageLoader
import com.shuyu.gsyimageloader.YAJLoadOption
import java.io.File

/**
 * Fresco 图片加载
 * Created by guoshuyu on 2018/1/19.
 */
class YAJFrescoImageLoader(private val context: Context, private var config: ImagePipelineConfig? = null) : YAJImageLoader, YAJFrescoFactory {

    init {
        if (config == null) {
            config = ImagePipelineConfig.newBuilder(context.applicationContext)
                    .setDownsampleEnabled(true)
                    .build()
        }
        Fresco.initialize(context.applicationContext, config)
    }

    override fun loadImage(loadOption: YAJLoadOption, target: Any?, callback: YAJImageLoader.Callback?, extendOption: YAJImageLoader.ExtendedOptions?) {

        try {
            val frescoView = target as SimpleDraweeView
            try {
                initFrescoView(frescoView, loadOption)
                val request = buildImageRequestWithResource(loadOption, extendOption)
                val lowRequest = buildLowImageRequest(frescoView, loadOption, extendOption)
                frescoView.controller = buildDraweeController(frescoView, loadOption, callback, request, lowRequest)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } catch (e: ClassCastException) {
            throw ClassCastException("use Fresco,You must use Fresco's control, SimpleDraweeView" + "\n" + "如果要使用Fresco，则图片控件必须使用SimpleDraweeView")
        }

    }

    override fun clearCache(type: Int) {
        when (type) {
            YAJImageConst.CLEAR_ALL_CACHE -> {
                Fresco.getImagePipeline().clearCaches()
            }
            YAJImageConst.CLEAR_MEMORY_CACHE ->
                Fresco.getImagePipeline().clearMemoryCaches()
            YAJImageConst.CLEAR_DISK_CACHE ->
                Fresco.getImagePipeline().clearDiskCaches()
        }
    }

    override fun clearCacheKey(type: Int, loadOption: YAJLoadOption) {
        val loadUri = getUri(loadOption.mUri)
        when (type) {
            YAJImageConst.CLEAR_ALL_CACHE -> {
                Fresco.getImagePipeline().evictFromCache(loadUri)
            }
            YAJImageConst.CLEAR_MEMORY_CACHE ->
                Fresco.getImagePipeline().evictFromMemoryCache(loadUri)
            YAJImageConst.CLEAR_DISK_CACHE ->
                Fresco.getImagePipeline().evictFromDiskCache(loadUri)

        }
    }

    override fun isCache(loadOption: YAJLoadOption, extendOption: YAJImageLoader.ExtendedOptions?): Boolean {
        val loadUri = getUri(loadOption.mUri)
        return isCached(context, loadUri)
    }

    override fun getLocalCache(loadOption: YAJLoadOption, extendOption: YAJImageLoader.ExtendedOptions?): File? {
        val loadUri = getUri(loadOption.mUri)
        if (!isCached(context, loadUri))
            return null
        val imageRequest = ImageRequest.fromUri(loadUri)
        val cacheKey = DefaultCacheKeyFactory.getInstance()
                .getEncodedCacheKey(imageRequest, context)
        val resource = ImagePipelineFactory.getInstance()
                .mainFileCache.getResource(cacheKey)
        return (resource as FileBinaryResource).file
    }

    override fun getLocalCacheBitmap(loadOption: YAJLoadOption, extendOption: YAJImageLoader.ExtendedOptions?): Bitmap? {
        val loadUri = getUri(loadOption.mUri)
        if (!isCached(context, loadUri))
            return null
        val request = buildImageRequestWithResource(loadOption, extendOption)
        val cacheKey = DefaultCacheKeyFactory.getInstance()
                .getBitmapCacheKey(request, context)
        val resource = ImagePipelineFactory.getInstance()
                .bitmapCountingMemoryCache.get(cacheKey)
        return (resource?.get() as CloseableBitmap).underlyingBitmap
    }


    override fun getCacheSize(): Long? {
        return ImagePipelineFactory.getInstance()
                .mainFileCache.size
    }

    override fun downloadOnly(loadOption: YAJLoadOption, callback: YAJImageLoader.Callback?, extendOption: YAJImageLoader.ExtendedOptions?) {
        val imageRequest = buildImageRequestWithResource(loadOption, extendOption)
        val imagePipeline = Fresco.getImagePipeline()
        val dataSource2 = imagePipeline.prefetchToDiskCache(imageRequest, context)
        dataSource2.subscribe(object : BaseDataSubscriber<Void>() {
            override fun onNewResultImpl(dataSource: DataSource<Void>) {
                val file = getLocalCache(loadOption, extendOption)
                callback?.onSuccess(file)
            }

            override fun onFailureImpl(dataSource: DataSource<Void>) {
                callback?.onFail(null)
            }
        }, CallerThreadExecutor.getInstance())

    }
}