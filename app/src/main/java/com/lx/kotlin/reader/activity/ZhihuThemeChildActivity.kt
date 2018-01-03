package com.lx.kotlin.reader.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.adapter.slimInjector.ThemeListInjector
import com.lx.kotlin.reader.model.bean.ThemeChildList
import com.lx.kotlin.reader.model.service.ServiceFactory
import com.lx.kotlin.reader.utils.Logger
import kotlinx.android.synthetic.main.activity_zhihu_theme_child.*
import kotlinx.android.synthetic.main.fragment_recycler.*
import net.idik.lib.slimadapter.SlimAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by liuxi on 2017/12/26.
 */
class ZhihuThemeChildActivity : BaseActivity() {

    var adapter: SlimAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zhihu_theme_child)
        var title = intent.getStringExtra("title")
        toolbar.setTitle(title)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_36dp)
        toolbar.setNavigationOnClickListener { finish() }
        initView()
        loadData()
    }

    private fun initView() {
        swipeRefresh.setOnRefreshListener { loadData() }
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SlimAdapter.create().register(R.layout.item_zhihu_theme, ThemeListInjector(this)).attachTo(recyclerView)
    }

    private fun loadData() {
        var id = intent.getIntExtra("id", 0)
        var api = ServiceFactory.getZhihuService()
        api.getZhihuThemeDetail(id).enqueue(object : Callback<ThemeChildList> {
            override fun onFailure(call: Call<ThemeChildList>?, t: Throwable?) {
                swipeRefresh.isRefreshing = false
                Logger.log("get daily failed")
            }
            override fun onResponse(call: Call<ThemeChildList>?, response: Response<ThemeChildList>?) {
                Logger.log("get daily sucess")
                adapter!!.updateData(response!!.body().stories)
                swipeRefresh.isRefreshing = false
            }
        })
    }

}