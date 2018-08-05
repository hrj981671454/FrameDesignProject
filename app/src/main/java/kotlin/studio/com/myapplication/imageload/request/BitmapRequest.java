package kotlin.studio.com.myapplication.imageload.request;

import android.widget.ImageView;

import java.lang.ref.SoftReference;
import java.util.Comparator;

import kotlin.studio.com.myapplication.imageload.config.DisplayConfig;
import kotlin.studio.com.myapplication.imageload.loader.SimpleImageLoader;
import kotlin.studio.com.myapplication.imageload.policy.LoadPolicy;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:23
 */
public class BitmapRequest implements Comparator<BitmapRequest> {

    /**
     * 加载策略
     */
    private LoadPolicy loadPolicy = SimpleImageLoader.getInstance().getConfig().getLoadPolicy();

    /**
     * 优先级编号
     */
    private int seriaNo;


    /**
     * 持有ImageView的软引用
     */
    private SoftReference<ImageView> imageViewSoftReference;


    /**
     * 图片路径
     */
    private String ImageUrl;


    /**
     * 名字合法化
     */
    private String imageUriMD5;


    /**
     * 下载完成监听
     */
    private SimpleImageLoader.ImageListener imageListener;

    private DisplayConfig displayConfig;


    public BitmapRequest(ImageView imageView, String imageUrl, DisplayConfig displayConfig, SimpleImageLoader.ImageListener imageListener) {
        this.imageViewSoftReference = new SoftReference<>(imageView);
        this.ImageUrl = imageUrl;
        this.imageListener = imageListener;
        imageView.setTag(imageUrl);
        if (null != displayConfig) {
            this.displayConfig = displayConfig;
        }
    }

    @Override
    public int compare(BitmapRequest bitmapRequest, BitmapRequest t1) {
        return loadPolicy.compareTo(bitmapRequest, t1);
    }


    public int getSeriaNo() {
        return seriaNo;
    }

    public void setSeriaNo(int seriaNo) {
        this.seriaNo = seriaNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BitmapRequest that = (BitmapRequest) o;

        if (seriaNo != that.seriaNo)
            return false;
        return loadPolicy != null ? loadPolicy.equals(that.loadPolicy) : that.loadPolicy == null;
    }

    @Override
    public int hashCode() {
        int result = loadPolicy != null ? loadPolicy.hashCode() : 0;
        result = 31 * result + seriaNo;
        return result;
    }

    public ImageView getImageView() {
        return imageViewSoftReference.get();
    }


    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getImageUriMD5() {
        return imageUriMD5;
    }

    public void setImageUriMD5(String imageUriMD5) {
        this.imageUriMD5 = imageUriMD5;
    }
}
