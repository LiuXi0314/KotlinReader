package com.lx.kotlin.reader.activity

import android.os.Bundle
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.adapter.FragmentAdapter
import com.lx.kotlin.reader.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 *首页
 */

class MainActivity : BaseActivity() {

    private var pageList: MutableList<BaseFragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        toolbar.setTitle(R.string.app_name)
        pageList.clear()

        var zhihuDaily = ZhihuDailyFragment()
        var zhihuDailyBundle = Bundle()
        zhihuDailyBundle.putString("title", getString(R.string.tab_zhihu_daily))
        zhihuDaily.arguments = zhihuDailyBundle
        pageList.add(zhihuDaily)



        var image = GirlsFragment()
        var imageBundle = Bundle()
        imageBundle.putString("title", getString(R.string.tab_gank_girls))
        image.arguments = imageBundle
        pageList.add(image)


        var zhihu = ZhihuThemeFragment()
        var zhihuBundle = Bundle()
        zhihuBundle.putString("title", getString(R.string.tab_zhihu_theme))
        zhihu.arguments = zhihuBundle
        pageList.add(zhihu)



        var one = DoubanFragment()
        var oneBundle = Bundle()
        oneBundle.putString("title", "豆瓣")
        one.arguments = oneBundle
        pageList.add(one)

        viewPager.adapter = FragmentAdapter(pageList, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

}
