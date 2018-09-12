package kotlin.studio.com.myapplication.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.eaju.imageloader.myLoader.config.DisplayConfig;
import com.eaju.imageloader.myLoader.core.SimpleImageLoader;

import kotlin.studio.com.myapplication.R;
import kotlin.studio.com.myapplication.app.App;
import kotlin.studio.com.myapplication.inject.ContentView;
import kotlin.studio.com.myapplication.inject.ViewInject;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-08-27 19:07
 */
@ContentView(R.layout.activity_image)
public class ImageActivity extends BaseActivity {

    @ViewInject(R.id.gv_view)
    GridView gridView;
    public final static String[] imageThumbUrls = new String[]{
            "http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383264_8243.jpg",
            "http://img.my.csdn.net/uploads/201407/26/1406383248_3693.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536661863480&di=714fa2842b2ac85e4551200517e191fe&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F89cccf8541eb324cc66a634775f2d58aa51efbc77e86a-eHjgcl_fw658",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536661863482&di=e098b4643578a5806583ce4323826cba&imgtype=0&src=http%3A%2F%2Fxuexi.leawo.cn%2Fuploads%2Fallimg%2F170401%2F14334544N-2.gif",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536661863483&di=dc459bb9d7dffb4e6b3ea69e9eb79d61&imgtype=0&src=http%3A%2F%2Fwww.zbjdyw.com%2Fqqwebhimgs%2Fuploads%2Fbd4345133.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536661863483&di=63638e30558aa701f04d2f7eb8bec2b4&imgtype=0&src=http%3A%2F%2Fa2.att.hudong.com%2F14%2F87%2F01300000369238123976878210084.gif",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536661863483&di=2f822bf0b357db62ed5a14cf79a702f8&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fadaf2edda3cc7cd936a0bff73901213fb80e910b.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537256487&di=640e992bf0805c657c0c7f97d05d050e&imgtype=jpg&er=1&src=http%3A%2F%2Fs16.sinaimg.cn%2Fbmiddle%2F6c00da43tda8745f4214f%26amp%3B690"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gridView.setAdapter(new MyAdapter(this));
    }

    class MyAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public MyAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return imageThumbUrls.length;
        }

        @Override
        public Object getItem(int position) {
            return imageThumbUrls[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, final View convertView, ViewGroup parent) {
            View item = inflater.inflate(R.layout.item, null);
            ImageView imageView = (ImageView) item.findViewById(R.id.iv);
            //请求图片
            DisplayConfig displayConfig = new DisplayConfig();
            displayConfig.failImage = R.mipmap.ic_launcher;
            displayConfig.loadingImage = R.mipmap.icon_xiaoyishangcheng;
        /*    App.getInstance().getImageLoader().loadImage(imageView, imageThumbUrls[position], displayConfig, new SimpleImageLoader.ImageListener() {
                @Override
                public void onComplete(ImageView imageView, Bitmap bitmap, String uri) {
                    System.out.println(imageView);
                    System.out.println(bitmap);
                    System.out.println(uri);
                }

                @Override
                public void onFail(Exception error) {

                }
            });*/
            App.getInstance()
                    .getImageLoader()
                    .loadImage(imageView, imageThumbUrls[position],
                            displayConfig, false, true,
                            null, new SimpleImageLoader.ImageListener() {
                                @Override
                                public void onComplete(ImageView imageView, Object bitmap, String uri) {
                                    System.out.println(imageView);
                                    System.out.println(bitmap);
                                    System.out.println(uri);
                                }

                                @Override
                                public void onFail(Exception error) {
                                }
                            });
            return item;
        }

    }
}
