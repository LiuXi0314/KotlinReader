package com.lx.kotlin.reader.adapter.slimInjector

import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.model.OneModel
import net.idik.lib.slimadapter.SlimInjector
import net.idik.lib.slimadapter.viewinjector.IViewInjector

/**
 * Created on 17-12-4 下午3:18
 */
class OneInjector : SlimInjector<OneModel> {

    override fun onInject(data: OneModel?, injector: IViewInjector<out IViewInjector<*>>?) {
        injector!!.text(R.id.name, data!!.name)
    }

}