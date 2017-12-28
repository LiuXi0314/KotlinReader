package com.lx.kotlin.reader.adapter

import android.content.Context
import com.lx.kotlin.reader.model.bean.ThemeList
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 * Created on 17-12-28 下午4:50
 */
class ZhiHuThemeAdapter(context: Context?, datas: MutableList<ThemeList.OthersInfo>?) : MultiItemTypeAdapter<ThemeList.OthersInfo>(context, datas) {

    override fun convert(holder: ViewHolder?, t: ThemeList.OthersInfo?) {
        super.convert(holder, t)

    }
}