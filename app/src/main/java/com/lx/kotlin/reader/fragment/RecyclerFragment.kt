package com.lx.kotlin.reader.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lx.kotlin.reader.R
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper
import kotlinx.android.synthetic.main.fragment_recycler.*

/**
 * 简单封装的列表Fragment
 * Created on 17-12-22 下午1:49
 */
abstract class RecyclerFragment<T> : BaseFragment(), View.OnClickListener, MultiItemTypeAdapter.OnItemClickListener {

    var adapter: MultiItemTypeAdapter<T>? = null
    private var headerAndFooterWrapper: HeaderAndFooterWrapper<T>? = null
    private var loadMoreWrapper: LoadMoreWrapper<T>? = null
    var data: MutableList<T>? = null
    var canLoadMore: Boolean = false //控制是否可以加载更多
    var isLoading: Boolean = false //判断是否正在加载中
    var footView: TextView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_recycler, container, false)!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = createLayoutManager();
        if (recyclerView.layoutManager == null) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        data = ArrayList()
        adapter = createAdapter()
        adapter?.setOnItemClickListener(this)
        headerAndFooterWrapper = HeaderAndFooterWrapper(adapter)
        footView = LayoutInflater.from(context).inflate(R.layout.load_more_view, null) as TextView?
        footView!!.layoutParams = ViewGroup.LayoutParams(-1,-2)
        hiddenFootView()
        headerAndFooterWrapper!!.addFootView(footView)
        loadMoreWrapper = LoadMoreWrapper(headerAndFooterWrapper)
        loadMoreWrapper!!.setOnLoadMoreListener {
            if (!isLoading && canLoadMore) {
                loadMore()
            }
        }

        recyclerView.adapter = loadMoreWrapper

        swipeRefresh.setOnRefreshListener { loadData() }
        swipeRefresh.setColorSchemeResources(R.color.colorRefresh1,
                R.color.colorRefresh2, R.color.colorRefresh3,
                R.color.colorRefresh4, R.color.colorRefresh5)
    }

    open fun createLayoutManager(): RecyclerView.LayoutManager? {
        return null
    }

    abstract fun createAdapter(): MultiItemTypeAdapter<T>

    abstract fun loadData()

    open fun loadMore() {
        if (isLoading) return
        isLoading = true
        footView!!.layoutParams.height = -2
        footView!!.text = "加载中..."

    }

    fun hiddenFootView() {
        footView!!.layoutParams.height = 0
    }

    open fun loadFinish() {
        footView!!.layoutParams.height = -2
        footView!!.text = "——我也是有底线的——"

    }

    fun refreshing() {
        swipeRefresh.post {
            swipeRefresh.isRefreshing = true
            loadData()
        }
    }


    fun closeRefresh() {
        swipeRefresh.isRefreshing = false
    }

    override fun onClick(v: View?) {
    }

    fun notifyDataChanged() {
        loadMoreWrapper?.notifyDataSetChanged()
        adapter?.notifyDataSetChanged()
    }

    /**
     * 刷新数据
     */
    fun update(list: MutableList<T>) {
        data!!.clear()
        add(list)
    }

    /**
     * 添加数据
     */
    fun add(list: MutableList<T>) {
        for (item in list) {
            data!!.add(item)
        }
        notifyDataChanged()
    }

    override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
        return true
    }

    override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {

    }


}