package com.lx.kotlin.reader.adapter.slimInjector

import android.content.Context
import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.activity.DailyListActivity
import com.lx.kotlin.reader.model.bean.ThemeList
import net.idik.lib.slimadapter.SlimInjector
import net.idik.lib.slimadapter.viewinjector.IViewInjector

/**
 * Created on 17-12-4 下午3:18
 */
class ZhihuThemeInjector(private var context: Context) : SlimInjector<ThemeList.OthersInfo> {

    override fun onInject(data: ThemeList.OthersInfo?, injector: IViewInjector<out IViewInjector<*>>?) {
        injector!!.text(R.id.name, data!!.name)
        injector!!.text(R.id.content, data!!.description)
        Glide.with(context).load(data!!.thumbnail).crossFade().into(injector.findViewById(R.id.image))
        injector.clicked(R.id.itemContent,object : View.OnClickListener{
            override fun onClick(v: View?) {
                var intent= Intent().setClass(context, DailyListActivity::class.java)
                intent.putExtra("title",data!!.name)
                        .putExtra("id",data!!.id)
                context.startActivity(intent)
            }

        })
    }

}