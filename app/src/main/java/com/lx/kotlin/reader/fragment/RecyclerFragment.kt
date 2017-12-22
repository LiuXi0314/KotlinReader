package com.lx.kotlin.reader.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lx.kotlin.reader.R
import kotlinx.android.synthetic.main.fragment_recycler.*
import net.idik.lib.slimadapter.SlimAdapter

/**
 * Created on 17-12-22 下午1:49
 */
abstract class RecyclerFragment : BaseFragment() {

    var mAdapter: SlimAdapter? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_recycler, container, false)!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = createLayoutManager();
        if (recyclerView.layoutManager == null) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        mAdapter = createAdapter();
        recyclerView.adapter = mAdapter
    }

    abstract fun createLayoutManager(): RecyclerView.LayoutManager?
    abstract fun createAdapter(): SlimAdapter?

}