package com.lx.kotlin.reader.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lx.kotlin.reader.utils.log

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log(localClassName)
    }
}
