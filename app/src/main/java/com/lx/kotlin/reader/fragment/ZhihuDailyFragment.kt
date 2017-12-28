package com.lx.kotlin.reader.fragment

import android.os.Bundle
import android.view.View
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.adapter.slimInjector.ZhihuThemeInjector
import com.lx.kotlin.reader.model.bean.ThemeList
import com.lx.kotlin.reader.model.service.ServiceFactory
import com.lx.kotlin.reader.utils.Logger
import kotlinx.android.synthetic.main.fragment_recycler.*
import net.idik.lib.slimadapter.SlimAdapter
import net.idik.lib.slimadapter.ex.loadmore.SlimMoreLoader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created on 17-12-4 下午2:42
 */
class ZhihuDailyFragment : RecyclerFragment() {

    override fun createMoreLoader(): SlimMoreLoader? {
        return object :SlimMoreLoader(context){
            override fun hasMore(): Boolean {
                return true
            }

            override fun onLoadMore(handler: Handler?) {
               Logger.log("load more")
            }

        }

    }


    override fun createAdapter(): SlimAdapter? {
        return SlimAdapter.create().register(R.layout.item_zhihu_theme, ZhihuThemeInjector(context))
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    override fun loadData() {

        var api = ServiceFactory.getZhihuService()
        api.getZhihuTheme().enqueue(object : Callback<ThemeList> {
            override fun onFailure(call: Call<ThemeList>?, t: Throwable?) {
                Logger.log("failure")
                Logger.log(t.toString())
                swipeRefresh.isRefreshing = false
            }
            override fun onResponse(call: Call<ThemeList>?, response: Response<ThemeList>?) {
                swipeRefresh.isRefreshing = false
                Logger.log("success" + response!!.body()!!.toString())
                mAdapter!!.updateData(response.body()!!.others)
                Logger.log("success")

            }
        })
    }


}