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
 * 简单封装的列表Fragment
 * Created on 17-12-22 下午1:49
 */
abstract class RecyclerFragment<T> : BaseFragment(),View.OnClickListener,MultiItemTypeAdapter.OnItemClickListener {

    var adapter: MultiItemTypeAdapter<T>? = null
    private var headerAndFooterWrapper: HeaderAndFooterWrapper<T>? = null
    private var loadMoreWrapper: LoadMoreWrapper<T>? = null
    var data: MutableList<T>? = null
    var canLoadMore: Boolean = false

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
        loadMoreWrapper = LoadMoreWrapper(headerAndFooterWrapper)
        loadMoreWrapper!!.setOnLoadMoreListener { loadData() }
        loadMoreWrapper!!.setLoadMoreView(createLoadMoreViewId())
        recyclerView.adapter = loadMoreWrapper

        swipeRefresh.setOnRefreshListener { loadData() }
        swipeRefresh.setColorSchemeResources(R.color.colorOrange,R.color.colorGreen,R.color.colorBlueGreen)
    }

    open fun createLayoutManager(): RecyclerView.LayoutManager?{
        return null
    }

    abstract fun createAdapter(): MultiItemTypeAdapter<T>

    abstract fun loadData()

    open fun loadMore(){

     }

    /**
     * 想要加载更多必须设置loadMoreViewId
     */
   open fun createLoadMoreViewId():Int{
        return 0
    }

    fun refreshLoad(){
        swipeRefresh.isRefreshing = true
    }

    override fun onClick(v: View?) {
    }

    fun notifyDataChanged(){
        loadMoreWrapper?.notifyDataSetChanged()
        adapter?.notifyDataSetChanged()
    }

    /**
     * 刷新数据
     */
    fun update(list: MutableList<T>){
        data!!.clear()
        add(list)
    }

    /**
     * 添加数据
     */
    fun add(list: MutableList<T>){
        for (item in list){
            data!!.add(item)
        }
        notifyDataChanged()
    }

    override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}