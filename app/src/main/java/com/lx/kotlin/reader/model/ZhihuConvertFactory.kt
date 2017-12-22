package com.lx.kotlin.reader.model

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created on 17-12-22 下午3:35
 */
class ZhihuConvertFactory : Converter.Factory() {

    override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *> {
        return super.responseBodyConverter(type, annotations, retrofit)
    }

    override fun requestBodyConverter(type: Type?, parameterAnnotations: Array<out Annotation>?, methodAnnotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody> {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
    }
}