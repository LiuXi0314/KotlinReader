package com.lx.kotlin.reader.adapter

import android.content.Context
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.model.bean.ThemeList
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import com.zhy.adapter.recyclerview.base.ItemViewDelegate
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 * Created on 17-12-28 下午4:50
 */
class ZhiHuThemeAdapter(context: Context, datas: MutableList<ThemeList.OthersInfo>?) : MultiItemTypeAdapter<ThemeList.OthersInfo>(context, datas) {

    init {
        addItemViewDelegate(ThemeItem(context))
    }

    private class ThemeItem(context: Context) :ItemViewDelegate<ThemeList.OthersInfo>{

        override fun convert(holder: ViewHolder?, t: ThemeList.OthersInfo?, position: Int) {

        }

        override fun isForViewType(item: ThemeList.OthersInfo?, position: Int): Boolean {
            return true
        }

        override fun getItemViewLayoutId(): Int {
            return R.layout.item_zhihu_theme
        }
    }
}