package com.lx.kotlin.reader.utils

import android.content.Context

/**
 * Created on 18-1-5 下午2:59
 */
object Formatter {
    fun isEmpty(str: String?): Boolean {
        return str == null || str.isEmpty()
    }

    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    fun dip2px(context: Context, dipValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    fun formatImageUrl(url: String?): String {

        if (url == null) return ""
        var str = url + "?imageView2/0/w/500"
        return url
    }
}