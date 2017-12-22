package com.lx.kotlin.reader.fragment

import android.support.v7.widget.RecyclerView
import com.lx.kotlin.reader.adapter.AdapterFactory
import com.lx.kotlin.reader.adapter.helper.AdapterType
import com.lx.kotlin.reader.model.ZhihuModel
import net.idik.lib.slimadapter.SlimAdapter

/**
 * Created on 17-12-4 下午2:42
 */
class ZhihuFragment : RecyclerFragment() {
    override fun createAdapter(): SlimAdapter? {
        return AdapterFactory().create(AdapterType.TYPE_ZHIHU)
    }

    override fun createLayoutManager(): RecyclerView.LayoutManager? {
        return null
    }

    override fun onResume() {
        super.onResume()
        var data = ZhihuModel()
        data.name = "2017 回顾丨千万国人，Steam 吃鸡！"
        data.content = "2017 即将翻篇，一组文章带你回顾这一年" +
                "的数码科技界，此为第一篇传说很久以前，拉斯维加斯赌场的鸡肉饭售价 1.79 美元，" +
                "那时赢一个赌注对应 2 美元，所以赌徒赢了一局，就有一顿饱饭可以吃了。在 2008 年上映的好莱坞" +
                "电影《决胜 21 点》中，Winner Winner Chicken Dinner 的台词让更多人知道了这句美式俚语。"
        var list: MutableList<ZhihuModel> = ArrayList()
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        list.add(data)
        mAdapter!!.updateData(list)
    }
}