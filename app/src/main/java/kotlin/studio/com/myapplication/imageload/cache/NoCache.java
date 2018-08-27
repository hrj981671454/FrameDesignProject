package kotlin.studio.com.myapplication.imageload.cache;


import android.graphics.Bitmap;

import kotlin.studio.com.myapplication.imageload.request.BitmapRequest;


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
