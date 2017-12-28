package com.lx.kotlin.reader.fragment

import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.adapter.slimInjector.ZhihuThemeInjector
import com.lx.kotlin.reader.model.bean.ThemeList
import com.lx.kotlin.reader.model.service.ServiceFactory
import com.lx.kotlin.reader.utils.Logger
import kotlinx.android.synthetic.main.fragment_recycler.*
import net.idik.lib.slimadapter.SlimAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created on 17-12-4 下午2:42
 */
class ZhihuThemeFragment : RecyclerFragment() {

    override fun createAdapter(): SlimAdapter? {
        return SlimAdapter.create().register(R.layout.item_zhihu_theme, ZhihuThemeInjector(context))
    }

    override fun onResume() {
        super.onResume()
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
                Logger.log("success"+  response!!.body()!!.toString())
                mAdapter!!.updateData(response.body()!!.others)
                Logger.log("success")
            }
        })
    }


}