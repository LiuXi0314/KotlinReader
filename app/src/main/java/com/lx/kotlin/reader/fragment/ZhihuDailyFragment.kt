package com.lx.kotlin.reader.fragment

import android.os.Bundle
import android.view.View
import com.lx.kotlin.reader.model.bean.ThemeList
import com.lx.kotlin.reader.model.service.ServiceFactory
import com.lx.kotlin.reader.utils.Logger
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import kotlinx.android.synthetic.main.fragment_recycler.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created on 17-12-4 下午2:42
 */
class ZhihuDailyFragment{
//    override fun loadData() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun createAdapter(): ? {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

//    override fun createAdapter(): MultiItemTypeAdapter<Any>? {
////        return SlimAdapter.create().register(R.layout.item_zhihu_theme, ZhihuThemeInjector(context))
//        return null
//    }
//
//    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        loadData()
//    }
//
//    override fun loadData() {
//
//        var api = ServiceFactory.getZhihuService()
//        api.getZhihuTheme().enqueue(object : Callback<ThemeList> {
//            override fun onFailure(call: Call<ThemeList>?, t: Throwable?) {
//                Logger.log("failure")
//                Logger.log(t.toString())
//                swipeRefresh.isRefreshing = false
//            }
//            override fun onResponse(call: Call<ThemeList>?, response: Response<ThemeList>?) {
//                swipeRefresh.isRefreshing = false
//                Logger.log("success" + response!!.body()!!.toString())
////                mAdapter!!.updateData(response.body()!!.others)
//                Logger.log("success")
//
//            }
//        })
//    }


}