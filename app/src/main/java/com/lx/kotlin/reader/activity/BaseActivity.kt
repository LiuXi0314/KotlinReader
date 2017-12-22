package com.lx.kotlin.reader.activity

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.utils.log



open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log(localClassName)
        //设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH && !isMiUi()) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorWhite));
        }
        if (isMiUi()) {
            setStatusBarDarkMode(true);
        }
    }

    @TargetApi(21)
    fun setStatusBarDarkMode(darkmode: Boolean) {
        val clazz = window.javaClass
        try {
            var darkModeFlag = 0
            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            darkModeFlag = field.getInt(layoutParams)
            val extraFlagField = clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
            extraFlagField.invoke(window, if (darkmode) darkModeFlag else 0, darkModeFlag)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun isMiUi(): Boolean {
        return Build.MANUFACTURER.toLowerCase() == "xiaomi"
    }
}
