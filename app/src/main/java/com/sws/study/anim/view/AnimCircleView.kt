package com.sws.study.anim.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.sws.study.utils.dp

/**
 * @author mengyuan
 * @date 2022/7/27/6:09 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 */

val BOTTOM_LINE_HEIGHT = 2f.dp

class AnimCircleView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) :
    View(context, attrs, defStyleAttr, defStyleRes) {

     var radius = 20f.dp
        set(value) {
            field = value
            invalidate()
        }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#242323")
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(
            width / 2f,
            height / 2f,
            radius,
            paint
        )
    }
}