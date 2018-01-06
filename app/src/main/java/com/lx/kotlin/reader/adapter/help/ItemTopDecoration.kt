package com.lx.kotlin.reader.adapter.help

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.utils.Formatter
import com.lx.kotlin.reader.utils.Logger


/**
 * 构建RecyclerView吸顶悬浮效果
 * data：记录每组数据第一个的位置
 * Created on 18-1-5 下午3:56
 */
class ItemTopDecoration(val context: Context, val data: MutableList<ItemDecData>) : RecyclerView.ItemDecoration() {

    private var topHeight: Float = 0F
    private val spacing = Formatter.dip2px(context, 4F)

    init {
        val paint = Paint()
        paint.setTextSize(Formatter.sp2px(context, 14.toFloat()).toFloat())
        paint.getTextBounds("test", 0, 4, Rect())
        val fontMetrics = paint.getFontMetricsInt()
        var absHeight = Math.abs(fontMetrics.top - fontMetrics.bottom)
        topHeight = (absHeight + 20).toFloat()
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        // 得到item真实的left和right（减去parent的padding）
        val left = parent!!.getPaddingLeft().toFloat()
        val right = (parent.getWidth() - parent.getPaddingRight()).toFloat()

        for (i in 0 until parent.getChildCount()) {

            // 直接获得的child只有当前显示的，所以就算i是0的index也只是当前第一个，而不是所有第一个
            val child = parent.getChildAt(i)
            val index = parent.getChildAdapterPosition(child)
            if (!checkInGroupFirst(index)) {
                continue
            }
            // 每组第一个item都留有空间来绘制
            val top = child.top - topHeight - spacing
            val bottom = child.top.toFloat() - spacing
            // 绘制背景色
            val paint = Paint()
            paint.setColor(ContextCompat.getColor(context, R.color.colorRefresh2))

            c!!.drawRect(left, top, right, bottom, paint)
            // 绘制组名
            paint.setColor(ContextCompat.getColor(context, R.color.colorWhite))
            paint.setTextSize(Formatter.sp2px(context, 12.toFloat()).toFloat())
            paint.setTextAlign(Paint.Align.LEFT)
            paint.setAntiAlias(true)
            var str = getText(index)
            paint.getTextBounds(str, 0, str.length, Rect())
            val fontMetrics = paint.getFontMetricsInt()
            val baseline = (bottom + top - fontMetrics.bottom - fontMetrics.top) / 2
            c.drawText(getText(index), left + 20, baseline, paint)
        }
    }


    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)
        // 得到item真实的left和right（减去parent的padding）
        val left = parent!!.getPaddingLeft().toFloat()
        val right = (parent.getWidth() - parent.getPaddingRight()).toFloat()

        var index  = (parent.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        val child = parent.getChildAt(0)

        // 每组第一个item都留有空间来绘制
        var top = 0F
        var bottom = 0F
        if (checkInGroupFirst(index+1)) {
            bottom = Math.min(topHeight + parent.paddingTop, child.bottom.toFloat() +spacing)
        } else {
            bottom = topHeight + parent.paddingTop
        }
        top = bottom - topHeight

        // 绘制背景色
        val paint = Paint()
        paint.setColor(ContextCompat.getColor(context, R.color.colorRefresh2))

        c!!.drawRect(left, top, right, bottom, paint)
        // 绘制组名
        paint.setColor(ContextCompat.getColor(context, R.color.colorWhite))
        paint.setTextSize(Formatter.sp2px(context, 12.toFloat()).toFloat())
        paint.setTextAlign(Paint.Align.LEFT)
        paint.setAntiAlias(true)
        var text = getText(index)
        Logger.log(text)
        paint.getTextBounds(text, 0, text.length, Rect())
        val fontMetrics = paint.getFontMetricsInt()
        val baseline = (bottom + top - fontMetrics.bottom - fontMetrics.top) / 2
        c.drawText(text, left + 20, baseline, paint)

    }

    /**
     * 为每组数据的第一个添加上间距
     */
    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        var pos = parent!!.getChildAdapterPosition(view)
        outRect!!.left = spacing
        outRect!!.right = spacing
        outRect.bottom = spacing
        if (checkInGroupFirst(pos)) {
            outRect!!.top = topHeight.toInt() + spacing
        }
    }

    private fun checkInGroupFirst(pos: Int): Boolean {
        for (item in data) {
            if (pos == item.pos) return true
        }
        return false
    }

    private fun getText(pos: Int): String {
        var id = getFirstId(pos)
        for (item in data){
            if (id == item.pos)return item.text
        }
        return ""
    }

    private fun getFirstId(pos: Int): Int {
        var lastData: ItemDecData? = null
        for (item in data) {
            if (pos == item.pos)return item.pos

            if (pos < item.pos) {
                return lastData!!.pos
            }

            lastData = item
        }
        return 0
    }
}