package com.lx.kotlin.reader.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.lx.kotlin.reader.adapter.GankGirlsAdapter
import com.lx.kotlin.reader.model.bean.Girls
import com.lx.kotlin.reader.model.service.ServiceFactory
import com.lx.kotlin.reader.utils.Formatter
import com.lx.kotlin.reader.utils.Logger
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created on 17-12-4 下午2:42
 */
class GirlsFragment : RecyclerFragment<Girls.Girl>() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshing()
    }

    var pageNo = 1
    override fun loadData() {
        pageNo = 1
        isLoading = true
        ServiceFactory.getGankService().getGirls(pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Girls>() {
                    override fun onError(e: Throwable?) {
                        closeRefresh()
                        isLoading = false
                    }

                    override fun onNext(t: Girls?) {
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

    override fun createAdapter(): MultiItemTypeAdapter<Girls.Girl> {
        return GankGirlsAdapter(context, data!!)
    }

    override fun loadMore() {
        super.loadMore()
        isLoading = true
        ServiceFactory.getGankService().getGirls(pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Girls>() {
                    override fun onError(e: Throwable?) {
                        Logger.log(e.toString())
                        loadMoreFinish()
                    }

                    override fun onNext(t: Girls?) {
                        if (t?.results == null || t!!.results!!.isEmpty()) loadFinish()
                        pageNo++
                        add(t!!.results!!)
                    }

                    override fun onCompleted() {
                        loadMoreFinish()
                    }

                })
    }

    override fun createLayoutManager(): RecyclerView.LayoutManager? {
        return GridLayoutManager(context,2)
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
            outRect!!.bottom = Formatter.dip2px(context,6f)
            outRect!!.left = Formatter.dip2px(context,2f)
            outRect!!.right = Formatter.dip2px(context,3f)
        }


    }

}