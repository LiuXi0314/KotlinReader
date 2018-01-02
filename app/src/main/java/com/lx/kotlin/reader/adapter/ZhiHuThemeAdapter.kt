package com.lx.kotlin.reader.adapter

import android.content.Context
import android.widget.TextView
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

    private class ThemeItem(val context: Context) : ItemViewDelegate<ThemeList.OthersInfo> {
        override fun convert(holder: ViewHolder?, data: ThemeList.OthersInfo?, position: Int) {
            holder?.getView<TextView>(R.id.name)?.text = data?.name
            holder?.getView<TextView>(R.id.content)?.text = data?.description
//            Glide.with(context).load(data?.thumbnail).crossFade().into(holder?.getView(R.id.image))
        }

        override fun isForViewType(item: ThemeList.OthersInfo?, position: Int): Boolean {
            return true
        }

        override fun getItemViewLayoutId(): Int {
            return R.layout.item_zhihu_theme
        }
    }
}