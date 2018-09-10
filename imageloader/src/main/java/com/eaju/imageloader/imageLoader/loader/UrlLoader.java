package com.eaju.imageloader.imageLoader.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

import com.eaju.imageloader.imageLoader.request.BitmapRequest;
import com.eaju.imageloader.imageLoader.utils.BitmapDecoder;
import com.eaju.imageloader.imageLoader.utils.ImageViewHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Description: 网络图片加载器
 * Copyright  : Copyright (c) 2018
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-08-24 10:49
 */
public class UrlLoader extends AbstractLoader {

    @Override
    protected Bitmap onLoad(final BitmapRequest request) {
        downloadImgByUrl(request.getImageUri(), getCache(request));

        BitmapDecoder decoder = new BitmapDecoder() {
            @Override
            public Bitmap decodeBitmapWithOption(Options options) {
                return BitmapFactory.decodeFile(getCache(request).getAbsolutePath(), options);
            }
        };

        return decoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView()), ImageViewHelper.getImageViewHeight(request.getImageView()));
    }

    /**
     * 根据url下载图片在指定的文件
     * @param urlStr
     * @param file
     * @return
     */
    public static boolean downloadImgByUrl(String urlStr, File file) {
        FileOutputStream fos = null;
        InputStream is = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            is = conn.getInputStream();
            fos = new FileOutputStream(file);
            byte[] buf = new byte[512];
            int len = 0;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
            }

            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
            }
        }

        return false;

    }

    private File getCache(BitmapRequest request) {
        File file = new File(request.getImageCachePath());
        if (!file.exists()) {
            file.mkdir();
        }
        return new File(file, request.getImageUriMD5());
    }


}
