package com.lx.kotlin.reader.adapter.help

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.lx.kotlin.reader.R
import com.lx.kotlin.reader.utils.Formatter


/**
 * 构建RecyclerView吸顶悬浮效果
 * data：记录每组数据第一个的位置
 * Created on 18-1-5 下午3:56
 */
class ItemTopDecoration(val context: Context, val data: MutableList<ItemDecData>, val topHeight: Float) : RecyclerView.ItemDecoration() {


    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)

//
//        // 得到item真实的left和right（减去parent的padding）
//        val left = parent!!.getPaddingLeft().toFloat()
//        val right = (parent.getWidth() - parent.getPaddingRight()).toFloat()
//
//        for (i in 0 until parent.getChildCount()) {
//            // 直接获得的child只有当前显示的，所以就算i是0的index也只是当前第一个，而不是所有第一个
//            val child = parent.getChildAt(i)
//            val index = parent.getChildAdapterPosition(child)
//            if (checkInGroupFirst(index)) {
//                // 每组第一个item都留有空间来绘制
//                val top = child.top - topHeight
//                var bottom: Float
//                if (checkInGroupFirst(index + 1)) {
//                    // 下一个组马上到达顶部
//                    bottom = Math.min(child.getBottom().toFloat(), topHeight)
//                } else {
//                    // 普通情况
//                    bottom = topHeight
//                }
//                // 绘制背景色
//                val paint = Paint()
//                paint.setColor(Color.WHITE)
//                c!!.drawRect(left, top, right, bottom, paint)
//            }
//        }

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
            if (checkInGroupFirst(index)) {
                // 每组第一个item都留有空间来绘制
                val top = child.top - topHeight
                val bottom = child.top.toFloat()
                // 绘制背景色
                val paint = Paint()
                paint.setColor(ContextCompat.getColor(context, R.color.colorRefresh2))

                c!!.drawRect(left, top, right, bottom, paint)
                // 绘制组名
                paint.setColor(ContextCompat.getColor(context, R.color.colorWhite))
                paint.setTextSize(Formatter.sp2px(context, 14.toFloat()).toFloat())
                paint.setTextAlign(Paint.Align.LEFT)
                paint.setAntiAlias(true)
                var str = getText(index)
                paint.getTextBounds(str, 0, str.length, Rect())
                val fontMetrics = paint.getFontMetricsInt()
                val baseline =bottom- (topHeight - fontMetrics.bottom + fontMetrics.top)
                c.drawText(getText(index), left + 20, baseline, paint)
            }
        }
    }

    /**
     * 为每组数据的第一个添加上间距
     */
    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        var pos = parent!!.getChildAdapterPosition(view)

        if (checkInGroupFirst(pos)) {
            outRect!!.top = topHeight.toInt()
        }
    }

    private fun checkInGroupFirst(pos: Int): Boolean {
        for (item in data) {
            if (pos == item.pos) return true
        }
        return false
    }

    private fun getText(pos: Int): String {
        for (item in data) {
            if (pos == item.pos) return item.text
        }
        return ""
    }
}