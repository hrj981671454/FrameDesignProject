package com.eaju.imageloader.myLoader.policy;


import com.eaju.imageloader.myLoader.request.BitmapRequest;

public class ReversePolicy implements LoadPolicy {

	@Override
	public int compareTo(BitmapRequest request1, BitmapRequest request2) {
		return request2.getSerialNO() - request1.getSerialNO();
	}

}
