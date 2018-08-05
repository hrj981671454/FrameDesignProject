package kotlin.studio.com.myapplication.imageload.loader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import kotlin.studio.com.myapplication.R;
import kotlin.studio.com.myapplication.imageload.cache.BitmapCache;
import kotlin.studio.com.myapplication.imageload.config.DisplayConfig;
import kotlin.studio.com.myapplication.imageload.request.BitmapRequest;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:24
 */
public abstract class AbstarctLoader implements Loader {
    private BitmapCache bitmapCache = SimpleImageLoader.getInstance().getConfig().getBitmapCache();


    private DisplayConfig displayConfig = SimpleImageLoader.getInstance().getConfig().getDisplayConfig();

    @Override
    public void loadImage(BitmapRequest request) {
        if (null != request) {
            Bitmap bitmap = bitmapCache.get(request);

            if (null == bitmap) {

                showLoadingImage(request);

                bitmap = onLoad(request);

                cacheBitmap(request, bitmap);
            }
        }

    }

    /**
     * 缓存图片
     * @param request
     * @param bitmap
     */
    private void cacheBitmap(BitmapRequest request, Bitmap bitmap) {
        if (null != request && null != bitmap) {
            synchronized (AbstarctLoader.class) {
                bitmapCache.put(request, bitmap);
            }
        }
    }

    /**
     * 加载图片
     * @param request
     * @return
     */
    protected abstract Bitmap onLoad(BitmapRequest request);

    protected void showLoadingImage(BitmapRequest request) {
        if (hasLoadingPlaceHolder()) {
            final ImageView imageView = request.getImageView();
            if (null != imageView) {
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(R.mipmap.ic_launcher);
                    }
                });
            }
        }
    }


    protected boolean hasLoadingPlaceHolder() {
        return (displayConfig != null && displayConfig.loadingImage > 0);
    }
}

