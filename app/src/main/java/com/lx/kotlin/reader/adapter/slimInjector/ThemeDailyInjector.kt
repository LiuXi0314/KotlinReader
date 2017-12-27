package com.lx.kotlin.reader.adapter.slimInjector

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.activity.NewsActivity
import com.lx.kotlin.reader.model.bean.DailyList
import com.lx.kotlin.reader.utils.Logger
import net.idik.lib.slimadapter.SlimInjector
import net.idik.lib.slimadapter.viewinjector.IViewInjector

/**
 * Created by liuxi on 2017/12/26.
 */
class ThemeDailyInjector(private var context: Context) : SlimInjector<DailyList.StoriesInfo>{
    override fun onInject(data: DailyList.StoriesInfo?, injector: IViewInjector<out IViewInjector<*>>?) {
        Logger.log(data.toString())
        injector!!.text(R.id.name, data!!.title)
        Glide.with(context).load(data.images?.first()).crossFade().into(injector.findViewById(R.id.image))

        injector.clicked(R.id.itemContent,{
            var intent = Intent().setClass(context, NewsActivity::class.java)
            intent.putExtra("title",data.title).putExtra("id",data.id)
            context.startActivity(intent)
        })
    }
}