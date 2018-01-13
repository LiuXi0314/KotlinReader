package com.lx.kotlin.reader.adapter

import android.content.Context
import android.widget.ImageView
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.model.bean.Girls
import com.lx.kotlin.reader.utils.DeviceUtils
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


    inner class ItemView(val context: Context) : ItemViewDelegate<Girls.Girl> {
        private val imageWidth = (DeviceUtils.getWidth(context)-Formatter.dip2px(context,12f))/2
        private val imageHeight = imageWidth*1.4
        override fun convert(holder: ViewHolder?, t: Girls.Girl?, position: Int) {
            var image = holder!!.getView<ImageView>(R.id.itemImage)
            image.measure(0,0)
            image.layoutParams.width = imageWidth
            image.layoutParams.height = imageHeight.toInt()
            ImageUtils.load(context, image, Formatter.formatImageUrl(t?.url))
        }

        override fun getItemViewLayoutId(): Int {
            return R.layout.item_gank_girl
        }

        override fun isForViewType(item: Girls.Girl?, position: Int): Boolean {
            return true
        }

    }
}