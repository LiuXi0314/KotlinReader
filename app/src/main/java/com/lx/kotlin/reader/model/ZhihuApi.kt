package com.lx.kotlin.reader.model

import retrofit2.Call
import retrofit2.http.GET


/**
 * Created on 17-12-22 下午3:40
 */
interface ZhihuApi {

    @GET("api/4/theme/10")
    fun getZhihuData():Call<ZhihuModel>



}