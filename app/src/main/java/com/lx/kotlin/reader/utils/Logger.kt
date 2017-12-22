package com.lx.kotlin.reader.utils

import android.util.Log

/**
 * Created on 17-12-4 上午11:28
 */
object Logger{
    private val TAG = "READER"

    fun log(str: String) {
        Log.d(TAG, str)
    }
}
