package com.eaju.imageloader.myLoader.loader;


import com.eaju.imageloader.myLoader.request.BitmapRequest;

public interface Loader {

	/**
	 * 加载图片
	 * @param request
	 */
	void loadImage(BitmapRequest request);
	
}
