package com.lx.kotlin.reader.utils

import android.content.Context

/**
 * Created on 18-1-13 下午5:37
 */
object DeviceUtils{
    fun getWidth(context: Context):Int{
        return context.resources.displayMetrics.widthPixels
    }

    fun getHeight(context: Context):Int{
        return context.resources.displayMetrics.heightPixels
    }
}