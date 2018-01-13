package com.lx.kotlin.reader.adapter

import android.content.Context
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.model.bean.Girls
import com.lx.kotlin.reader.utils.Formatter
import com.lx.kotlin.reader.utils.ImageUtils
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import com.zhy.adapter.recyclerview.base.ItemViewDelegate
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 * Created on 18-1-13 下午3:10
 */
class GankGirlsAdapter(context: Context, data: MutableList<Girls.Girl>) : MultiItemTypeAdapter<Girls.Girl>(context, data) {

    init {
        addItemViewDelegate(ItemView(context))
    }

    class ItemView(val context: Context) : ItemViewDelegate<Girls.Girl> {
        override fun convert(holder: ViewHolder?, t: Girls.Girl?, position: Int) {
            ImageUtils.load(context, holder!!.getView(R.id.itemImage), Formatter.formatImageUrl(t?.url))
        }

        override fun getItemViewLayoutId(): Int {
            return R.layout.item_gank_girl
        }

        override fun isForViewType(item: Girls.Girl?, position: Int): Boolean {
            return true
        }

    }
}