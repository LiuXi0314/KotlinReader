package com.lx.kotlin.reader.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * 图片加载工具类
 * Created on 18-1-5 上午11:14
 */
object ImageUtils {

    fun load(context: Context?, view: ImageView?, imageUrl: String?) {
        Glide.with(context).load(imageUrl).crossFade().into(view)
    }

}