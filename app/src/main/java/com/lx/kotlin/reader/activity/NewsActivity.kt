package com.lx.kotlin.reader.activity

import android.os.Bundle
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.model.bean.News
import com.lx.kotlin.reader.model.service.ServiceFactory
import com.lx.kotlin.reader.utils.HtmlUtils
import com.lx.kotlin.reader.utils.Logger
import kotlinx.android.synthetic.main.activity_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 故事/消息详情页面
 *
 * Created by liuxi on 2017/12/27.
 */
class NewsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        var title = intent.getStringExtra("title")
        toolbar.setTitle(title)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_36dp)
        toolbar.setNavigationOnClickListener { finish() }
        loadData()
    }

    private fun loadData() {
        var id = intent.getIntExtra("id", 0)
        var api = ServiceFactory.getZhihuService()
        api.getZhihuNews(id).enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>?, t: Throwable?) {

                Logger.log("get news failed")
            }

            override fun onResponse(call: Call<News>?, response: Response<News>?) {
                Logger.log("get news sucess")
                var news = response!!.body()
                toolbar.setTitle(news!!.title)
                webView.loadData(HtmlUtils.structHtml(news?.body.toString(), news.css!!), "text/html; charset=UTF-8", null)
            }
        })
    }
}