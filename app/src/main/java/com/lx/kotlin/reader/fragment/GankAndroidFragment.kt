package com.lx.kotlin.reader.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.lx.kotlin.reader.adapter.GankAndroidAdapter
import com.lx.kotlin.reader.model.bean.Android
import com.lx.kotlin.reader.model.service.ServiceFactory
import com.lx.kotlin.reader.utils.Formatter
import com.lx.kotlin.reader.utils.Logger
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 干货Android 列表
 * Created on 17-12-4 下午2:42
 */
class GankAndroidFragment : RecyclerFragment<Android.ResultsInfo>() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshing()
    }

    var pageNo = 1
    override fun loadData() {
        pageNo = 1
        isLoading = true
        ServiceFactory.getGankService().getAndroid(pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Android>() {
                    override fun onError(e: Throwable?) {
                        closeRefresh()
                        isLoading = false
                    }

                    override fun onNext(t: Android?) {
                        pageNo++
                        update(t!!.results!!)
                    }

                    override fun onCompleted() {
                        closeRefresh()
                        isLoading = false
                        canLoadMore = true
                    }

                })
    }

    override fun createAdapter(): MultiItemTypeAdapter<Android.ResultsInfo> {
        return GankAndroidAdapter(context, data!!)
    }

    override fun loadMore() {
        super.loadMore()
        isLoading = true
        ServiceFactory.getGankService().getAndroid(pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Android>() {
                    override fun onError(e: Throwable?) {
                        Logger.log(e.toString())
                        loadMoreFinish()
                    }

                    override fun onNext(t: Android?) {
                        if (t?.results == null || t!!.results!!.isEmpty()) loadFinish()
                        pageNo++
                        add(t!!.results!!)
                    }

                    override fun onCompleted() {
                        loadMoreFinish()
                    }

                })
    }


    override fun createItemDecoration(): RecyclerView.ItemDecoration? {
        return MyItemDecoration()
    }

    override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
        super.onItemClick(view, holder, position)

    }


    inner class MyItemDecoration : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect!!.bottom = Formatter.dip2px(context,4f)
            outRect!!.left = Formatter.dip2px(context,4f)
            outRect!!.right = Formatter.dip2px(context,4f)
        }


    }
}