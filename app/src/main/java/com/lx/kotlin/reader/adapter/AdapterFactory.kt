package com.lx.kotlin.reader.adapter

import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.adapter.helper.AdapterType
import com.lx.kotlin.reader.adapter.slimInjector.ImageInjector
import com.lx.kotlin.reader.adapter.slimInjector.OneInjector
import com.lx.kotlin.reader.adapter.slimInjector.ZhihuInjector
import net.idik.lib.slimadapter.SlimAdapter

/**
 * Created on 17-12-4 下午3:02
 */
class AdapterFactory {

    fun create(type: AdapterType): SlimAdapter? {

        var adapter = SlimAdapter.create();

        if (type == AdapterType.TYPE_ZHIHU) {
            adapter.register(R.layout.item_zhihu, ZhihuInjector())
        } else if (type == AdapterType.TYPE_ONE) {
            adapter.register(R.layout.item_one, OneInjector())
        } else {
            adapter.register(R.layout.item_image, ImageInjector())
        }
        return adapter

    }


}