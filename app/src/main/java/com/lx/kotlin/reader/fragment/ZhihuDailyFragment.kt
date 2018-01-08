package com.lx.kotlin.reader.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.lx.kotlin.reader.activity.NewsActivity
import com.lx.kotlin.reader.adapter.ZhihuDailyAdapter
import com.lx.kotlin.reader.adapter.help.ItemDecData
import com.lx.kotlin.reader.adapter.help.ItemTopDecoration
import com.lx.kotlin.reader.model.bean.DailyInfo
import com.lx.kotlin.reader.model.bean.StoriesInfo
import com.lx.kotlin.reader.model.service.ServiceFactory
import com.lx.kotlin.reader.utils.Formatter
import com.lx.kotlin.reader.utils.Logger
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 知乎日报列表
 * Created on 17-12-4 下午2:42
 */
class ZhihuDailyFragment : RecyclerFragment<StoriesInfo>() {

    var lastDate: String? = ""
    val topList: MutableList<ItemDecData> = ArrayList()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshing()
    }

    override fun loadData() {
        isLoading = true
        Logger.log("请求知乎日报")
        ServiceFactory.getZhihuService()
                .getDailyLatest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<DailyInfo>() {

                    override fun onError(e: Throwable?) {
                        closeRefresh()
                        isLoading = false
                    }

                    override fun onCompleted() {
                        closeRefresh()
                        isLoading = false
                        canLoadMore = true
                    }

                    override fun onNext(t: DailyInfo?) {
                        lastDate = t?.date
                        addTopData()
                        update(t!!.stories!!)

                    }

                })


    }

    private fun addTopData() {
        var pos = data!!.size
        if(pos < 0 ) pos = 0
        var itemDecData = ItemDecData(pos, lastDate!!)
        topList.add(itemDecData)
    }

    override fun createAdapter(): MultiItemTypeAdapter<StoriesInfo> {
        return ZhihuDailyAdapter(context, data)
    }

    override fun createLayoutManager(): RecyclerView.LayoutManager? {
        return LinearLayoutManager(context)
    }

    override fun loadMore() {
        super.loadMore()
        if (Formatter.isEmpty(lastDate)) {
            loadFinish()
            return
        }
        isLoading = true
        ServiceFactory.getZhihuService()
                .getDailyBefore(lastDate!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<DailyInfo>() {

                    override fun onError(e: Throwable?) {
                        Logger.log(e.toString())
                        loadMoreFinish()
                    }

                    override fun onCompleted() {
                        loadMoreFinish()
                    }

                    override fun onNext(t: DailyInfo?) {
                        if (t == null) loadFinish()
                        lastDate = t?.date
                        addTopData()
                        add(t!!.stories!!)
                    }
                })

    }

    override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
        super.onItemClick(view, holder, position)
        var data = data?.get(position)
        var intent = Intent().setClass(context, NewsActivity::class.java)
        intent.putExtra("title", data?.title).putExtra("id", data?.id)
        context.startActivity(intent)
    }

    override fun createItemDecoration(): RecyclerView.ItemDecoration? {
        return ItemTopDecoration(context, topList)
    }
}