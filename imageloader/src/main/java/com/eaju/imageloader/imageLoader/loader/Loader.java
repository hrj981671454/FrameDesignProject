package com.eaju.imageloader.imageLoader.loader;


import com.eaju.imageloader.imageLoader.request.BitmapRequest;

public interface Loader {

	/**
	 * 加载图片
	 * @param request
	 */
	void loadImage(BitmapRequest request);
	
}
