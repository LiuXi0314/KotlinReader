package com.lx.kotlin.reader.adapter

import android.content.Context
import android.widget.TextView
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.model.bean.StoriesInfo
import com.lx.kotlin.reader.utils.ImageUtils
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import com.zhy.adapter.recyclerview.base.ItemViewDelegate
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 *
 * 知乎日报adapter
 * Created on 18-1-5 上午11:06
 */
class ZhihuDailyAdapter(context: Context?, data: MutableList<StoriesInfo>?) : MultiItemTypeAdapter<StoriesInfo>(context, data) {

    init {
        addItemViewDelegate(DailyItem(context))
    }

    class DailyItem(val context: Context?) : ItemViewDelegate<StoriesInfo> {
        override fun convert(holder: ViewHolder?, data: StoriesInfo?, position: Int) {
            holder?.getView<TextView>(R.id.name)?.text = data?.title
            ImageUtils.load(context, holder?.getView(R.id.image), data?.images!![0])
        }

        override fun getItemViewLayoutId(): Int {
            return R.layout.item_zhihu_daily
        }

        override fun isForViewType(item: StoriesInfo?, position: Int): Boolean {
            return true
        }

    }
}