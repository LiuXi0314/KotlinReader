package com.lx.kotlin.reader.model.api

import com.lx.kotlin.reader.model.bean.DailyInfo
import com.lx.kotlin.reader.model.bean.News
import com.lx.kotlin.reader.model.bean.ThemeChildList
import com.lx.kotlin.reader.model.bean.ThemeList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable


/**
 * Created on 17-12-22 下午3:40
 */
interface ZhihuApi {

    //使用Retrofit 默认方式返回数据
    @GET(ApiConstance.BODY_ZHIHU_THEME)
    fun getZhihuTheme(): Call<ThemeList>

    @GET("api/4/theme/{id}")
    fun getZhihuThemeDetail(@Path("id") id: Int): Call<ThemeChildList>

    @GET("api/4/news/{id}")
    fun getZhihuNews(@Path("id") id: Int): Call<News>


    /**
     * 使用RxJava方式返回数据
     */
    @GET("api/4/news/latest")
    fun getDailyLatest(): Observable<DailyInfo>


    @GET("api/4/news/before/{date}")
    fun getDailyBefore(@Path("date") date: String): Observable<DailyInfo>
}