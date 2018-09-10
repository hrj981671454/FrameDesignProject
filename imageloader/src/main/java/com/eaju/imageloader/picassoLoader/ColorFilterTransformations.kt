package com.eaju.imageloader.picassoLoader

import android.graphics.*
import com.squareup.picasso.Transformation

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:34
 */
class ColorFilterTransformations(private val mColor: Int) : Transformation {

    override fun transform(source: Bitmap): Bitmap {

        val width = source.width
        val height = source.height

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.colorFilter = PorterDuffColorFilter(mColor, PorterDuff.Mode.SRC_ATOP)
        canvas.drawBitmap(source, 0f, 0f, paint)
        source.recycle()

        return bitmap
    }

    override fun key(): String {
        return "ColorFilterTransformation(color=$mColor)"
    }
}