package kotlin.studio.com.myapplication.imageload.loader;


import kotlin.studio.com.myapplication.imageload.request.BitmapRequest;

public interface Loader {

	/**
	 * 加载图片
	 * @param request
	 */
	void loadImage(BitmapRequest request);
	
}
