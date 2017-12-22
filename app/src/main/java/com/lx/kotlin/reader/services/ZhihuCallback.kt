package com.lx.kotlin.reader.services

import com.lx.kotlin.reader.model.ZhihuModel
import com.lx.kotlin.reader.utils.Logger
import net.idik.lib.slimadapter.SlimAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created on 17-12-22 下午5:50
 */
class ZhihuCallback(var adapter: SlimAdapter?):Callback<MutableList<ZhihuModel>>{
    override fun onResponse(call: Call<MutableList<ZhihuModel>>?, response: Response<MutableList<ZhihuModel>>?) {
        Logger.log(response!!.message())
        Logger.log(response!!.isSuccess.toString() )
        Logger.log(response!!.headers().toString() )
        Logger.log(response!!.body().toString() )

    }

    override fun onFailure(call: Call<MutableList<ZhihuModel>>?, t: Throwable?) {

    }

}