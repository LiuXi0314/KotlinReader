package com.lx.kotlin.reader.model.api

import com.lx.kotlin.reader.model.bean.Theme
import retrofit2.Call
import retrofit2.http.GET


/**
 * Created on 17-12-22 下午3:40
 */
interface ZhihuApi {

    @GET("api/4/themes")
    fun getZhihuData():Call<Theme>



}