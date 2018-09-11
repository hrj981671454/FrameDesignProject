package com.eaju.imageloader.myLoader.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;

import com.eaju.imageloader.myLoader.request.BitmapRequest;
import com.eaju.imageloader.myLoader.utils.BitmapDecoder;
import com.eaju.imageloader.myLoader.utils.ImageViewHelper;

import java.io.File;


/**
 * Description: 本地图片加载器
 * Copyright  : Copyright (c) 2018
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-08-25 12:56
 */
public class LocalLoader extends AbstractLoader {

	@Override
	protected Bitmap onLoad(BitmapRequest request) {
		final String path = Uri.parse(request.getImageUri()).getPath();
		File file = new File(path);
		if(!file.exists()){
			return null;
		}
		BitmapDecoder decoder = new BitmapDecoder() {
			@Override
			public Bitmap decodeBitmapWithOption(Options options) {
				return BitmapFactory.decodeFile(path, options);
			}
		};
		
		return decoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView()), ImageViewHelper.getImageViewHeight(request.getImageView()));
	}

}
