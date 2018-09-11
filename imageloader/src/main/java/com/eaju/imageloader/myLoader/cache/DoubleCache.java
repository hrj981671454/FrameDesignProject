package com.eaju.imageloader.myLoader.cache;

import android.content.Context;
import android.graphics.Bitmap;

import com.eaju.imageloader.myLoader.request.BitmapRequest;


/**
 * Description: 双重缓存
 * Copyright  : Copyright (c) 2018
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-08-30 13:13
 */
public class DoubleCache implements BitmapCache {
	
	//内存缓存
	private MemoryCache mMemoryCache = new MemoryCache();
	//硬盘缓存
	private DiskCache mDiskCache;
	
	public DoubleCache(Context context) {
		mDiskCache = DiskCache.getInstance(context);
	}

	@Override
	public void put(BitmapRequest request, Bitmap bitmap) {
		mMemoryCache.put(request, bitmap);
		mDiskCache.put(request, bitmap);
	}

	@Override
	public Bitmap get(BitmapRequest request) {
		Bitmap bitmap = mMemoryCache.get(request);
		if(bitmap == null){
			bitmap = mDiskCache.get(request);
			if(bitmap != null){
				//放入内存，方便再获取
				mMemoryCache.put(request, bitmap);
			}
		}
		return bitmap;
	}

	@Override
	public void remove(BitmapRequest request) {
		mMemoryCache.remove(request);
		mDiskCache.remove(request);
	}

}
