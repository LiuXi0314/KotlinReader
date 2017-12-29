package com.lx.kotlin.reader.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lx.kotlin.reader.R
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper
import kotlinx.android.synthetic.main.fragment_recycler.*

/**
 * Created on 17-12-22 下午1:49
 */
abstract class RecyclerFragment : BaseFragment(), View.OnClickListener {

    var adapter: MultiItemTypeAdapter<Any>? = null
    private var headerAndFooterWrapper: HeaderAndFooterWrapper<Any>? = null
    private var loadMoreWrapper: LoadMoreWrapper<Any>? = null
    var data: MutableList<Any>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_recycler, container, false)!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = createLayoutManager();
        if (recyclerView.layoutManager == null) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        adapter = createAdapter()
        headerAndFooterWrapper = HeaderAndFooterWrapper(adapter)
        loadMoreWrapper = LoadMoreWrapper(headerAndFooterWrapper)
        loadMoreWrapper!!.setOnLoadMoreListener { loadData() }
        recyclerView.adapter = adapter

        swipeRefresh.setOnRefreshListener { loadData() }
    }

    fun createLayoutManager(): RecyclerView.LayoutManager? {
        return null
    }

    abstract fun createAdapter(): MultiItemTypeAdapter<Any>?

    abstract fun loadData()

    fun refreshLoad(){
        swipeRefresh.isRefreshing = true
    }

    override fun onClick(v: View?) {
    }

    fun notifyDataChanged(){
        loadMoreWrapper?.notifyDataSetChanged()
        adapter?.notifyDataSetChanged()
    }

}