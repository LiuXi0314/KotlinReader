package com.lx.kotlin.reader.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.lx.kotlin.reader.activity.NewsActivity
import com.lx.kotlin.reader.adapter.ZhihuDailyAdapter
import com.lx.kotlin.reader.model.bean.DailyLatest
import com.lx.kotlin.reader.model.bean.StoriesInfo
import com.lx.kotlin.reader.model.service.ServiceFactory
import com.lx.kotlin.reader.utils.Logger
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *
 * 知乎日报列表
 * Created on 17-12-4 下午2:42
 */
class ZhihuDailyFragment : RecyclerFragment<StoriesInfo>() {

    override fun createAdapter(): MultiItemTypeAdapter<StoriesInfo> {
        return ZhihuDailyAdapter(context,data)
    }

    override fun loadData() {
        Logger.log("请求知乎日报")
        ServiceFactory.getZhihuService()
                .getDailyLatest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Subscriber<DailyLatest>(){

                    override fun onError(e: Throwable?) {
                        closeRefresh()
                    }

                    override fun onCompleted() {
                        closeRefresh()
                    }

                    override fun onNext(t: DailyLatest?) {
                        update(t!!.stories!!)
                    }


                })



    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshing()
    }

    override fun createLayoutManager(): RecyclerView.LayoutManager? {
        return LinearLayoutManager(context)
    }

    override fun loadMore() {
        super.loadMore()
    }

    override fun onClick(v: View?) {
        super.onClick(v)
    }

    override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
        super.onItemClick(view, holder, position)
        var data = data?.get(position)
        var intent = Intent().setClass(context, NewsActivity::class.java)
        intent.putExtra("title",data?.title).putExtra("id",data?.id)
        context.startActivity(intent)
    }
}