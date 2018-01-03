package com.lx.kotlin.reader.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.lx.kotlin.reader.activity.DailyListActivity
import com.lx.kotlin.reader.adapter.ZhiHuThemeAdapter
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
class ZhihuThemeFragment : RecyclerFragment<ThemeList.OthersInfo>() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        canLoadMore = false
        loadData()
    }
    override fun createAdapter(): MultiItemTypeAdapter<ThemeList.OthersInfo> {
        return ZhiHuThemeAdapter(context,data)
    }

    override fun createLayoutManager(): RecyclerView.LayoutManager? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadData() {
        Logger.log("请求数据")
        var api = ServiceFactory.getZhihuService()
        api.getZhihuTheme().enqueue(object : Callback<ThemeList> {
            override fun onFailure(call: Call<ThemeList>?, t: Throwable?) {
                Logger.log("failure")
                Logger.log(t.toString())
                swipeRefresh.isRefreshing = false
            }
            override fun onResponse(call: Call<ThemeList>?, response: Response<ThemeList>?) {
                swipeRefresh.isRefreshing = false
                Logger.log("success"+  response!!.body()!!.toString())
                update(response.body().others!!)
                Logger.log("success")
            }
        })
    }

    override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
        var data = data?.get(position)
        var intent= Intent().setClass(context, DailyListActivity::class.java)
        intent.putExtra("title",data!!.name)
                .putExtra("id",data!!.id)
        context.startActivity(intent)
    }


}