package com.lx.kotlin.reader.model.api

import com.lx.kotlin.reader.model.bean.Girls
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * 干货集中营Api
 * http://gank.io/api
 * Created on 18-1-13 下午2:45
 */
interface GankApi {

    /**
     * 15 -> limit
     */
    @GET("data/%E7%A6%8F%E5%88%A9/15/{pageNo}")
    fun getGirls(@Path("pageNo") pageNo: Int): Observable<Girls>

}