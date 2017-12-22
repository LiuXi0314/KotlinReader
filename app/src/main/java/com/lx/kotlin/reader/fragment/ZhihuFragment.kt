package com.lx.kotlin.reader.fragment

import android.support.v7.widget.RecyclerView
import com.lx.kotlin.reader.adapter.AdapterFactory
import com.lx.kotlin.reader.adapter.helper.AdapterType
import com.lx.kotlin.reader.model.ZhihuApi
import com.lx.kotlin.reader.utils.Toaster
import kotlinx.android.synthetic.main.fragment_recycler.*
import net.idik.lib.slimadapter.SlimAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created on 17-12-4 下午2:42
 */
class ZhihuFragment : RecyclerFragment() {

    override fun createAdapter(): SlimAdapter? {
        return AdapterFactory().create(AdapterType.TYPE_ZHIHU)
    }

    override fun createLayoutManager(): RecyclerView.LayoutManager? {
        return null
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }


    override fun loadData() {

        android.os.Handler().postDelayed(
                {
                    Toaster.show(context, "加载中")
                    swipeRefresh.isRefreshing = false;
                }, 1000)
        service()

    }

    private fun service() {
        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        var client = OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()

        var retrofit = Retrofit.Builder()
                .baseUrl("https://news-at.zhihu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        var api = retrofit.create(ZhihuApi::class.java)
        api.getZhihuData()

    }

}