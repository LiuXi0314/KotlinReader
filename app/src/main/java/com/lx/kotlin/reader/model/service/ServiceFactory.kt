package com.lx.kotlin.reader.model.service

import com.lx.kotlin.reader.model.api.ApiConstance
import com.lx.kotlin.reader.model.api.ZhihuApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created on 17-12-25 上午11:39
 */
object ServiceFactory {

    fun getClient():OkHttpClient{
        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return  OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()
    }

    fun getZhihuService():ZhihuApi{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava
                .baseUrl(ApiConstance.HEADER_ZHIHU)
                .client(getClient())
                .build()
                .create(ZhihuApi::class.java)
    }

}
