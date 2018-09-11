package com.eaju.imageloader.myLoader.cache;


import android.graphics.Bitmap;

import com.eaju.imageloader.myLoader.request.BitmapRequest;


/**
 * Description: 无缓存
 * Copyright  : Copyright (c) 2018
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-08-30 14:55
 */
public class NoCache implements BitmapCache {

	@Override
	public void put(BitmapRequest request, Bitmap bitmap) {
		// TODO Auto-generated method stub

	}

	@Override
	public Bitmap get(BitmapRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(BitmapRequest request) {
		// TODO Auto-generated method stub

	}

}
