package kotlin.studio.com.myapplication.imageload.loader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import kotlin.studio.com.myapplication.imageload.cache.BitmapCache;
import kotlin.studio.com.myapplication.imageload.config.DisplayConfig;
import kotlin.studio.com.myapplication.imageload.core.SimpleImageLoader;
import kotlin.studio.com.myapplication.imageload.request.BitmapRequest;


/**
 * 抽象加载器
 * @author Jason
 * QQ: 1476949583
 * @version 1.0
 */
public abstract class AbstractLoader implements Loader {

    //缓存策略
    protected BitmapCache   mCache         = SimpleImageLoader.getInstance().getConfig().getBitmapCache();
    //显示配置
    private   DisplayConfig mDisplayConfig = SimpleImageLoader.getInstance().getConfig().getDisplayConfig();

    /**
     * 模板方法
     */
    @Override
    public void loadImage(BitmapRequest request) {
        //从缓存获取，缓存中没有再加载
        Bitmap bitmap = mCache.get(request);
        if (bitmap == null) {
            //加载前显示的图片
            showLoadingImage(request);

            //加载完成，再缓存
            //具体的加载方式，由子类决定
            bitmap = onLoad(request);
            cacheBitmap(request, bitmap);
        }

        //显示
        deliveryToUIThread(request, bitmap);
    }

    /**
     * 加载前显示的图片
     * @param request
     */
    protected void showLoadingImage(BitmapRequest request) {
        //指定了，显示配置
        if (hasLoadingPlaceHolder()) {
            final ImageView imageView = request.getImageView();
            if (imageView != null) {
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(mDisplayConfig.loadingImage);
                    }
                });
            }

        }
    }

    protected boolean hasLoadingPlaceHolder() {
        return (mDisplayConfig != null && mDisplayConfig.loadingImage > 0);
    }

    protected boolean hasFailedPlaceHolder() {
        return (mDisplayConfig != null && mDisplayConfig.failImage > 0);
    }

    /**
     * 交给主线程显示
     * @param request
     * @param bitmap
     */
    protected void deliveryToUIThread(final BitmapRequest request, final Bitmap bitmap) {
        ImageView imageView = request.getImageView();
        if (imageView != null) {
            imageView.post(new Runnable() {
                @Override
                public void run() {
                    updateImageView(request, bitmap);
                }

            });
        }

    }


    private void updateImageView(final BitmapRequest request, final Bitmap bitmap) {
        ImageView imageView = request.getImageView();
        //加载正常
        if (bitmap != null && imageView.getTag().equals(request.getImageUri())) {
            imageView.setImageBitmap(bitmap);
        }
        //有可能加载失败
        if (bitmap == null && hasFailedPlaceHolder()) {
            imageView.setImageResource(mDisplayConfig.failImage);
        }
        //监听
        //回调
        if (request.imageListener != null) {
            request.imageListener.onComplete(imageView, bitmap, request.getImageUri());
        }
    }

    /**
     * 缓存
     * @param request
     * @param bitmap
     */
    private void cacheBitmap(BitmapRequest request, Bitmap bitmap) {
        if (request != null && bitmap != null) {
            synchronized (mCache) {
                mCache.put(request, bitmap);
            }
        }
    }

    /**
     * 具体的加载实现
     * @param request
     * @return
     */
    protected abstract Bitmap onLoad(BitmapRequest request);


}
