package com.sws.study.anim.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.sws.study.utils.dp

/**
 * @author mengyuan
 * @date 2022/7/27/6:09 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 */


class PointView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) :
    View(context, attrs, defStyleAttr, defStyleRes) {

    var point = PointF(0f, 0f)
        set(value) {
            field = value
            invalidate()
        }


    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#242323")
        strokeWidth = 20.dp
        strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPoint(point.x, point.y, paint)
    }
}