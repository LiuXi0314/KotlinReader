package com.lx.kotlin.reader.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.lx.kotlin.reader.R

/**
 * 图片加载工具类
 * Created on 18-1-5 上午11:14
 */
object ImageUtils {

    fun load(context: Context?, view: ImageView?, imageUrl: String?) {
        Glide.with(context).load(imageUrl).placeholder(R.drawable.background).crossFade().into(view)
    }

}