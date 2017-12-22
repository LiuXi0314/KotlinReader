package com.lx.kotlin.reader.utils

import android.content.Context
import android.widget.Toast

/**
 * Toast 封装工具类，避免同一时间创建多个Toast
 * Created on 17-12-22 下午3:02
 */
object Toaster {
    private var toast: Toast? = null

    fun show(context: Context, str: String) {
        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(str)
        }
        toast!!.show()

    }

    fun show(context: Context, strId: Int) {
        show(context, context.getString(strId))
    }
}