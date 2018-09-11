package com.eaju.imageloader.myLoader.loader;

import android.graphics.Bitmap;
import android.util.Log;

import com.eaju.imageloader.myLoader.request.BitmapRequest;


/**
 * Description: “空”加载器
 * Copyright  : Copyright (c) 2018
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-08-25 12:56
 */
public class NullLoader extends AbstractLoader {

	@Override
	protected Bitmap onLoad(BitmapRequest request) {
		Log.e("jason", "图片无法记载!");
		return null;
	}

}
