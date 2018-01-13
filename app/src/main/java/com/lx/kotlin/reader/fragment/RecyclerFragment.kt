package com.lx.kotlin.reader.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.model.bean.Girls
import com.lx.kotlin.reader.utils.Logger
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper
import kotlinx.android.synthetic.main.fragment_recycler.*

/**
 * 简单封装的列表Fragment
 * Created on 17-12-22 下午1:49
 */
abstract class RecyclerFragment<T> : BaseFragment(), MultiItemTypeAdapter.OnItemClickListener {

    var adapter: MultiItemTypeAdapter<T>? = null
    private var headerAndFooterWrapper: HeaderAndFooterWrapper<T>? = null
    private var loadMoreWrapper: LoadMoreWrapper<T>? = null
    var data: MutableList<T>? = null
    var canLoadMore: Boolean = false //控制是否可以加载更多
    var isLoading: Boolean = false //判断是否正在加载中
    var loadMoreView: TextView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_recycler, container, false)!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = createLayoutManager();
        if (recyclerView.layoutManager == null) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        var itemDec = createItemDecoration()
        if (itemDec != null) {
            recyclerView.addItemDecoration(itemDec)
        }
        data = ArrayList()
        adapter = createAdapter()
        adapter?.setOnItemClickListener(this)
        headerAndFooterWrapper = HeaderAndFooterWrapper(adapter)
        loadMoreView = LayoutInflater.from(context).inflate(R.layout.load_more_view, null) as TextView?
        loadMoreView!!.layoutParams = ViewGroup.LayoutParams(-1, -2)
        hiddenloadMoreView()
        loadMoreWrapper = LoadMoreWrapper(headerAndFooterWrapper)
        loadMoreWrapper!!.setOnLoadMoreListener {
            if (!isLoading && canLoadMore) {
                Logger.log("加载更多")
                loadMore()
            }
        }

        loadMoreWrapper!!.setLoadMoreView(loadMoreView)

        recyclerView.adapter = loadMoreWrapper

        swipeRefresh.setOnRefreshListener {
            canLoadMore = true
            loadData()
        }
        swipeRefresh.setColorSchemeResources(R.color.colorRefresh1,
                R.color.colorRefresh2, R.color.colorRefresh3,
                R.color.colorRefresh4, R.color.colorRefresh5)
    }

    abstract fun loadData()

    abstract fun createAdapter(): MultiItemTypeAdapter<T>

    open fun createLayoutManager(): RecyclerView.LayoutManager? {
        return null
    }

    open fun createItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    open fun loadMore() {
        if (isLoading) return
        isLoading = true
        loadMoreView!!.layoutParams.height = -2
        loadMoreView!!.text = "加载中..."

    }

    fun hiddenloadMoreView() {
        loadMoreView!!.layoutParams.height = 0
    }

    fun loadMoreFinish() {
        isLoading = false
        hiddenloadMoreView()
    }

    open fun loadFinish() {
        isLoading = false
        canLoadMore = false
        loadMoreView!!.layoutParams.height = -2
        loadMoreView!!.text = "——我也是有底线的——"
    }

    fun refreshing() {
        if (swipeRefresh == null) return
        swipeRefresh.post {
            swipeRefresh.isRefreshing = true
            loadData()
        }
    }

    fun closeRefresh() {
        if (swipeRefresh != null)
            swipeRefresh.isRefreshing = false
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
        data!!.addAll(list.toMutableList())
        loadMoreWrapper?.notifyDataSetChanged()
        adapter?.notifyDataSetChanged()
    }

    override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
        return true
    }

    override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        var s:List<Girls> = ArrayList()
        var a:List<Girls> = ArrayList()

    }
}