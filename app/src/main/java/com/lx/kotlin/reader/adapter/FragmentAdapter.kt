package com.lx.kotlin.reader.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.lx.kotlin.reader.fragment.BaseFragment

/**
 * Created on 17-12-4 下午1:21
 */
class FragmentAdapter(var pageList: MutableList<BaseFragment>?, var fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int = pageList!!.size

    override fun getItem(position: Int): Fragment = pageList!!.get(position)

    override fun getPageTitle(position: Int): CharSequence = pageList!!.get(position).arguments.getString("title")!!
}