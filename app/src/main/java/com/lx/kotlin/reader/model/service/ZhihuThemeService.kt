package com.lx.kotlin.reader.model.service

import com.lx.kotlin.reader.model.api.ApiConstance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created on 17-12-25 上午11:39
 */
class ZhihuThemeService<T>(t: T) {
    fun create(): T {
        return Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConstance.HEADER_ZHIHU)
                .build().create(T)
    }

}
