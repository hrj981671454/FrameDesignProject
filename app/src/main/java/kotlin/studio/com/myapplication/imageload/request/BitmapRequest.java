package kotlin.studio.com.myapplication.imageload.request;

import android.widget.ImageView;

import java.lang.ref.SoftReference;

import kotlin.studio.com.myapplication.imageload.config.DisplayConfig;
import kotlin.studio.com.myapplication.imageload.core.SimpleImageLoader;
import kotlin.studio.com.myapplication.imageload.policy.LoadPolicy;
import kotlin.studio.com.myapplication.imageload.utils.MD5Utils;

public class BitmapRequest implements Comparable<BitmapRequest> {
    //加载策略
    private LoadPolicy loadPolicy = SimpleImageLoader.getInstance().getConfig().getLoadPolicy();
    //序列号
    private int serialNO;

    //图片控件
    //当系统内存不足时，把引用的对象进行回收
    private SoftReference<ImageView> mimageViewRef;
    //图片路径
    private Object                   imageUri;
    //MD5的图片路径
    private String                   imageUriMD5;

    private DisplayConfig displayConfig = SimpleImageLoader.getInstance().getConfig().getDisplayConfig();
    public SimpleImageLoader.ImageListener imageListener;

    public BitmapRequest(ImageView imageView, Object uri, DisplayConfig config, SimpleImageLoader.ImageListener imageListener) {
        this.mimageViewRef = new SoftReference<ImageView>(imageView);
        //设置可见的ImageView的tag为，要下载的图片路径
        imageView.setTag(uri);
        this.imageUri = uri;
        this.imageUriMD5 = MD5Utils.toMD5(imageUri.toString());
        if (config != null) {
            this.displayConfig = config;
        }
        this.imageListener = imageListener;
    }

    @Override
    public int compareTo(BitmapRequest another) {
        return loadPolicy.compareTo(this, another);
    }

    /**
     * 设置序列号
     * @param serialNO
     */
    public void setSerialNO(int serialNO) {
        this.serialNO = serialNO;
    }

    public int getSerialNO() {
        return serialNO;
    }

    public ImageView getImageView() {
        return mimageViewRef.get();
    }

    public String getImageUri() {
        return imageUri.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((loadPolicy == null) ? 0 : loadPolicy.hashCode());
        result = prime * result + serialNO;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BitmapRequest other = (BitmapRequest) obj;
        if (loadPolicy == null) {
            if (other.loadPolicy != null)
                return false;
        } else if (!loadPolicy.equals(other.loadPolicy))
            return false;
        if (serialNO != other.serialNO)
            return false;
        return true;
    }

    public String getImageUriMD5() {
        return imageUriMD5;
    }


}
