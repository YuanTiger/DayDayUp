package com.sws.study.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.sws.study.utils.dp

/**
 * @author mengyuan
 * @date 2022/8/3/2:56 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 圆形ImageView
 */
class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var radius = 100.dp
    private var padding = 100.dp

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = ((radius + padding) * 2).toInt()
        val width = resolveSize(size, widthMeasureSpec)
        val height = resolveSize(size, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(padding + radius, padding + radius, radius, paint)
    }
}