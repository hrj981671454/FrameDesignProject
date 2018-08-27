package kotlin.studio.com.myapplication.imageload.loader;

import android.graphics.Bitmap;
import android.util.Log;

import kotlin.studio.com.myapplication.imageload.request.BitmapRequest;


/**
 * “空”加载器
 * @author Jason
 * QQ: 1476949583
 * @date 2016年3月11日
 * @version 1.0
 */
public class NullLoader extends AbstractLoader {

	@Override
	protected Bitmap onLoad(BitmapRequest request) {
		Log.e("jason", "图片无法记载!");
		return null;
	}

}
