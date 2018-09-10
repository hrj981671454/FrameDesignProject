package com.shuyu.gsygiideloader

import com.bumptech.glide.load.Key
import java.security.MessageDigest


/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-31 14:34
 */
class YAJGlideCacheKey constructor(private val id: String, private val signature: Key) : Key {

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }

        val that = o as YAJGlideCacheKey?

        return id == that!!.id && signature == that.signature
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + signature.hashCode()
        return result
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(id.toByteArray(charset(Key.STRING_CHARSET_NAME)))
        signature.updateDiskCacheKey(messageDigest)
    }
}