package com.eaju.imageloader.imageLoader.cache;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.eaju.imageloader.imageLoader.disk.DiskLruCache;
import com.eaju.imageloader.imageLoader.disk.IOUtil;
import com.eaju.imageloader.imageLoader.request.BitmapRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Description: 硬盘缓存（支持LRU算法）
 * Copyright  : Copyright (c) 2018
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-08-29 09:51
 */
public class DiskCache implements BitmapCache {

    private static DiskCache mDiskCache;
    //缓存路径
    private        String    mCacheDir;
    //MB
    private static final int MB = 1024 * 1024;
    //jackwharton的杰作
    private DiskLruCache mDiskLruCache;

    private Context mContext;

    private DiskCache(Context context) {
        this.mContext = context;
    }

    /**
     * 初始化
     * @param context
     */
    private void initDiskCache(Context context, BitmapRequest request) {
        this.mCacheDir = request.getImageCachePath();
        //        File directory = new File(request.getImageCachePath());
        File directory = getDiskCacheDir(request.getImageCachePath(), context);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        //初始化
        //1 每次缓存一个图片
        //50 MB 最大值
        try {
            mDiskLruCache = DiskLruCache.open(directory, getAppVersion(context), 1, request.getImageCacheSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DiskCache getInstance(Context context) {
        if (mDiskCache == null) {
            synchronized (DiskCache.class) {
                if (mDiskCache == null) {
                    mDiskCache = new DiskCache(context);
                }
            }
        }
        return mDiskCache;
    }

    /**
     * 获取应用的版本号
     * @param context
     * @return
     */
    private int getAppVersion(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            return pm.getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 获取缓存路径
     * @param mCacheDir
     * @param context
     * @return
     */
    private File getDiskCacheDir(String mCacheDir, Context context) {
        //相对路径
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            //外部存储
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            //内部存储
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separatorChar + mCacheDir);
    }


    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        if (null == mDiskLruCache) {
            initDiskCache(mContext, request);
        }
        DiskLruCache.Editor editor = null;
        OutputStream os = null;
        try {
            //开始编辑
            editor = mDiskLruCache.edit(request.getImageUriMD5());
            os = editor.newOutputStream(0);
            //成功，或者失败
            if (persistBitmap2Disk(bitmap, os)) {
                editor.commit();
            } else {
                editor.abort();
            }
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(os);
        }
    }

    /**
     * 持久化Bitmap对象到Disk
     * @param bitmap
     * @param os
     * @return
     */
    private boolean persistBitmap2Disk(Bitmap bitmap, OutputStream os) {
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(os);
            bitmap.compress(CompressFormat.JPEG, 100, bos);
            //清空缓存
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            IOUtil.closeQuietly(bos);
        }
        return true;
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        if (null == mDiskLruCache) {
            initDiskCache(mContext, request);
        }
        InputStream is = null;
        try {
            //快照
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(request.getImageUriMD5());
            is = snapshot.getInputStream(0);
            return BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(is);
        }
        return null;
    }

    @Override
    public void remove(BitmapRequest request) {
        if (null == mDiskLruCache) {
            initDiskCache(mContext, request);
        }
        try {
            mDiskLruCache.remove(request.getImageUriMD5());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
