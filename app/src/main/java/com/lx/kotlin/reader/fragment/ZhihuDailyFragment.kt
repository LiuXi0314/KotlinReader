package com.lx.kotlin.reader.fragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.lx.kotlin.reader.model.bean.StoriesInfo
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter

/**
 *
 * 知乎日报列表
 * Created on 17-12-4 下午2:42
 */
class ZhihuDailyFragment: RecyclerFragment<StoriesInfo>(){
    override fun createAdapter(): MultiItemTypeAdapter<StoriesInfo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun createLayoutManager(): RecyclerView.LayoutManager? {
        return super.createLayoutManager()
    }

    override fun loadMore() {
        super.loadMore()
    }

    override fun createLoadMoreViewId(): Int {
        return super.createLoadMoreViewId()
    }

    override fun onClick(v: View?) {
        super.onClick(v)
    }

    override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
        super.onItemClick(view, holder, position)
    }
}