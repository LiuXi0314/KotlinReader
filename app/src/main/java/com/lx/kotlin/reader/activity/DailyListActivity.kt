package com.lx.kotlin.reader.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.adapter.slimInjector.ThemeDailyInjector
import com.lx.kotlin.reader.model.bean.DailyList
import com.lx.kotlin.reader.model.service.ServiceFactory
import com.lx.kotlin.reader.utils.Logger
import kotlinx.android.synthetic.main.activity_theme_list.*
import kotlinx.android.synthetic.main.fragment_recycler.*
import net.idik.lib.slimadapter.SlimAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by liuxi on 2017/12/26.
 */
class DailyListActivity : BaseActivity() {

    var adapter: SlimAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme_list)
        var title = intent.getStringExtra("title")
        toolbar.setTitle(title)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.abc_ic_arrow_drop_right_black_24dp)
        toolbar.setNavigationOnClickListener { finish() }
        initView()
        loadData()
    }

    private fun initView() {
        swipeRefresh.setOnRefreshListener { loadData() }
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SlimAdapter.create().register(R.layout.item_zhihu_theme,ThemeDailyInjector(this)).attachTo(recyclerView)

    }

    private fun loadData() {
        var id = intent.getIntExtra("id", 0)
        var api = ServiceFactory.getZhihuService()
        api.getZhihuThemeDetail(id).enqueue(object : Callback<DailyList> {
            override fun onFailure(call: Call<DailyList>?, t: Throwable?) {
                swipeRefresh.isRefreshing = false
                Logger.log("get daily failed")
            }

            override fun onResponse(call: Call<DailyList>?, response: Response<DailyList>?) {
                Logger.log("get daily sucess")
                adapter!!.updateData(response!!.body().stories)
                swipeRefresh.isRefreshing = false
            }
        })
    }

}