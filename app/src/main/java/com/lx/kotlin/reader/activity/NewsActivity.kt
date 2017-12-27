package com.lx.kotlin.reader.activity

import android.os.Bundle
import android.text.Html
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.model.bean.News
import com.lx.kotlin.reader.model.service.ServiceFactory
import com.lx.kotlin.reader.utils.Logger
import kotlinx.android.synthetic.main.activity_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by liuxi on 2017/12/27.
 */
class NewsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        var title = intent.getStringExtra("title")
        toolbar.setTitle(title)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.abc_ic_arrow_drop_right_black_24dp)
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
                toolbar.setTitle(response?.body()?.title)
                content.setText(Html.fromHtml(response?.body()?.body))
            }
        })
    }
}