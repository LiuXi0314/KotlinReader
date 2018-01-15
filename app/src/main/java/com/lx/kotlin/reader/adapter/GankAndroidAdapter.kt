package com.lx.kotlin.reader.adapter

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.activity.WebActivity
import com.lx.kotlin.reader.model.bean.Android
import com.lx.kotlin.reader.utils.ImageUtils
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import com.zhy.adapter.recyclerview.base.ItemViewDelegate
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 * Created on 18-1-13 下午3:10
 */
class GankAndroidAdapter(context: Context, data: MutableList<Android.ResultsInfo>) : MultiItemTypeAdapter<Android.ResultsInfo>(context, data) {

    init {
        addItemViewDelegate(ItemView(context))
    }


    inner class ItemView(val context: Context) : ItemViewDelegate<Android.ResultsInfo> {

        override fun convert(holder: ViewHolder?, t: Android.ResultsInfo?, position: Int) {
            var image = holder!!.getView<ImageView>(R.id.image)
            if (t?.images != null && t?.images!!.isNotEmpty()) {
                ImageUtils.load(context, image, t?.images!![0])
            } else {
                image.setImageResource(R.drawable.background)
            }
            holder.getView<TextView>(R.id.title).text = t?.desc
            holder.getView<TextView>(R.id.type).text = t?.type
            holder.getView<TextView>(R.id.time).text = t?.publishedAt?.substring(0, 10)
            var author = t?.who
            if (author == null) author = ""
            holder.getView<TextView>(R.id.author).text = author.toString()
            holder.convertView.setOnClickListener {
                var intent = Intent(context, WebActivity::class.java)
                intent.putExtra("title", t?.desc)
                intent.putExtra("url", t?.url)
                context.startActivity(intent)
            }

        }

        override fun getItemViewLayoutId(): Int {
            return R.layout.item_gank_android
        }

        override fun isForViewType(item: Android.ResultsInfo?, position: Int): Boolean {
            return true
        }

    }
}