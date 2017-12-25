package com.lx.kotlin.reader.fragment

import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.adapter.slimInjector.ZhihuInjector
import com.lx.kotlin.reader.model.api.ApiConstance
import com.lx.kotlin.reader.model.api.ZhihuApi
import com.lx.kotlin.reader.model.bean.Theme
import com.lx.kotlin.reader.model.service.ZhihuThemeService
import com.lx.kotlin.reader.utils.Logger
import net.idik.lib.slimadapter.SlimAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created on 17-12-4 下午2:42
 */
class ZhihuFragment : RecyclerFragment() {

    override fun createAdapter(): SlimAdapter? {
        return SlimAdapter.create().register(R.layout.item_zhihu,ZhihuInjector(context))
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun loadData() {
        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        var client = OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()

        var retrofit = Retrofit.Builder()
                .baseUrl(ApiConstance.HEADER_ZHIHU)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        var api = retrofit.create(ZhihuApi::class.java)

        var apiNew = ZhihuThemeService(ZhihuApi::class.java).create()
        api.getZhihuTheme().enqueue(object : Callback<Theme> {
            override fun onFailure(call: Call<Theme>?, t: Throwable?) {
                Logger.log("failure")
                Logger.log(t.toString())
            }

            override fun onResponse(call: Call<Theme>?, response: Response<Theme>?) {
                Logger.log("success"+  response!!.body()!!.toString())
                mAdapter!!.updateData(response.body()!!.others)
                Logger.log("success")
            }
        })
    }


}