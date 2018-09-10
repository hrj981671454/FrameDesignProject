package com.eaju.imageloader.imageLoader.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/**
 * Description: 图片压缩器
 * Copyright  : Copyright (c) 2018
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-08-24 10:49
 */
public abstract class BitmapDecoder {


    public abstract Bitmap decodeBitmapWithOption(Options options);

    /**
     * 压缩图片
     * @param reqWidth
     *         指定要缩放后的宽度
     * @param reqHeight
     *         指定要缩放后的高度
     * @return
     */
    public Bitmap decodeBitmap(int reqWidth, int reqHeight) {
        //1.初始化Options
        Options options = new Options();
        //Bitmap对象不占用内存
        //只需要读取图片的宽高信息，无需将整张图片加载到内存中
        options.inJustDecodeBounds = true;
        //2.根据options加载Bitmap，图片的数据存储在options中（第一次读取图片的宽高）
        decodeBitmapWithOption(options);
        //3.计算图片缩放的比例
        calculateSampleSizeWithOption(options, reqWidth, reqHeight);
        //4.通过options设置的缩放比例记载图片（第二次根据缩放比例读取一个缩放后的图片）
        return decodeBitmapWithOption(options);
    }

    /**
     * 计算图片缩放的比例
     * @param options
     * @param reqWidth
     * @param reqHeight
     */
    private void calculateSampleSizeWithOption(Options options, int reqWidth, int reqHeight) {
        //计算缩放的比例
        //图片的原始宽高
        int width = options.outWidth;
        int height = options.outHeight;

        int inSampleSize = 1;
        if (width > reqWidth || height > reqHeight) {
            //宽高的缩放比例
            int heightRatio = Math.round((float) height / (float) reqHeight);
            int widthRatio = Math.round((float) width / (float) reqWidth);

            //有的图是长图、有的是宽图
            inSampleSize = Math.max(heightRatio, widthRatio);
        }

        //全景图
        //当inSampleSize为2，图片的宽与高变成原来的1/2
        //options.inSampleSize = 2
        options.inSampleSize = inSampleSize;

        //每个像素2个字节
        options.inPreferredConfig = Config.RGB_565;
        //Bitmap占用内存
        options.inJustDecodeBounds = false;
        //当系统内存不足时可以回收Bitmap
        options.inPurgeable = true;
        options.inInputShareable = true;


    }


    /**
     * 对bitmap进行压缩处理
     * @param source
     *         ：需要被处理的Bitmap
     * @param width
     *         需要压缩成的宽度 必须为浮点型
     * @param height
     *         需要压缩成的高度 必须为浮点型
     * @return 返回压缩后的Bitmap
     * 注意！必须提供参数2，3为浮点型。
     */
    public static Bitmap zoom(Bitmap source, float width, float height) {
        Matrix matrix = new Matrix();
        float scaleX = width / source.getWidth();
        float scaleY = height / source.getHeight();
        matrix.postScale(scaleX, scaleY);

        Bitmap bitmap = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
        return bitmap;
    }

    /**
     * 将矩形的Bitmap对象转换为圆形的Bitmap
     * @param source:待处理的
     *         矩形的Bitmap
     * @return ：需返回的圆形的Bitmap
     */
    public static Bitmap circleBitmap(Bitmap source) {
        //获取Bitmap的宽度
        int width = source.getWidth();
        //返回一个正方形的Bitmap对象
        Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        //提供指定宽高的canvas
        Canvas canvas = new Canvas(bitmap);
        //提供画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //背景：在画布上绘制一个圆
        canvas.drawCircle(width / 2, width / 2, width / 2, paint);

        //设置图片相交情况下的处理方式
        //setXfermode：设置当绘制的图像出现相交情况时候的处理方式的,它包含的常用模式有哪几种
        //PorterDuff.Mode.SRC_IN 取两层图像交集部门,只显示上层图像,注意这里是指取相交叉的部分,然后显示上层图像
        //PorterDuff.Mode.DST_IN 取两层图像交集部门,只显示下层图像
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //前景：在画布上绘制一个bitmap
        canvas.drawBitmap(source, 0, 0, paint);

        return bitmap;

    }


    // dp--px
    public static int dp2px(Context context, int dp) {
        // 1、获取屏幕密度
        float density = context.getResources().getDisplayMetrics().density;
        // 2、进行乘法操作
        return (int) (dp * density + 0.5);
    }


    // px--dp
    public static int px2dp(Context context,int px) {
        // 1、获取屏幕密度
        float density = context.getResources().getDisplayMetrics().density;
        // 2、进行除法操作
        return (int) (px / density + 0.5);
    }
}
