package com.lx.kotlin.reader.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created on 17-12-4 下午2:42
 */
class ImageFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var text = TextView(context)
        text.setText(arguments.getString("title"))
        text.layoutParams = ViewGroup.LayoutParams(-1, -2)
        return text
    }

}