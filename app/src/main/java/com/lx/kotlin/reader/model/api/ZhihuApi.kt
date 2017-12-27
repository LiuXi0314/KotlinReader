package com.lx.kotlin.reader.model.api

import com.lx.kotlin.reader.model.bean.DailyList
import com.lx.kotlin.reader.model.bean.News
import com.lx.kotlin.reader.model.bean.Theme
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created on 17-12-22 下午3:40
 */
interface ZhihuApi {

    @GET(ApiConstance.BODY_ZHIHU_THEME)
    fun getZhihuTheme(): Call<Theme>

    @GET("api/4/theme/{id}")
    fun getZhihuThemeDetail(@Path("id") id: Int): Call<DailyList>

    @GET("api/4/news/{id}")
    fun getZhihuNews(@Path("id")id:Int):Call<News>
}