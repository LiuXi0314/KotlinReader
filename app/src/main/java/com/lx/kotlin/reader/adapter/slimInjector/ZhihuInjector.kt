package com.lx.kotlin.reader.adapter.slimInjector

import android.content.Context
import com.bumptech.glide.Glide
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.model.bean.Theme
import net.idik.lib.slimadapter.SlimInjector
import net.idik.lib.slimadapter.viewinjector.IViewInjector

/**
 * Created on 17-12-4 下午3:18
 */
class ZhihuInjector(var context: Context) : SlimInjector<Theme.OthersInfo> {

    override fun onInject(data: Theme.OthersInfo?, injector: IViewInjector<out IViewInjector<*>>?) {
        injector!!.text(R.id.name, data!!.name)
        injector!!.text(R.id.content, data!!.description)
        Glide.with(context).load(data!!.thumbnail).crossFade().into(injector.findViewById(R.id.image))
    }

}