package kotlin.studio.com.myapplication.imageload.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import kotlin.studio.com.myapplication.imageload.request.BitmapRequest;


/**
 * 内存缓存
 * @author Jason
 * QQ: 1476949583
 * @version 1.0
 */
public class MemoryCache implements BitmapCache {

    private LruCache<String, Bitmap> mLruCache;

    public MemoryCache() {
        //缓存的最大值（可用内存的1/8）
        int maxSize = (int) Runtime.getRuntime().freeMemory() / 1024 / 8;
        mLruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //一个Bitmap的大小
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        mLruCache.put(request.getImageUriMD5(), bitmap);
        Log.d("jason", "put in MemoryCache:" + request.getImageUri());
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        Log.d("jason", "get from MemoryCache:" + request.getImageUri());
        return mLruCache.get(request.getImageUriMD5());
    }

    @Override
    public void remove(BitmapRequest request) {
        mLruCache.remove(request.getImageUriMD5());
    }

}
