package com.sws.study.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.sws.study.utils.dp
import java.util.*

/**
 * @author mengyuan
 * @date 2022/8/3/3:35 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 字体大小、背景颜色全部随机的TextView
 */
private val COLORS = intArrayOf(
    Color.parseColor("#0091ff"),
    Color.parseColor("#FFCC45"),
    Color.parseColor("#242323"),
    Color.parseColor("#795444"),
    Color.parseColor("#FF5722"),
    Color.parseColor("#22CD00"),
)
private val TEXT_SIZE = intArrayOf(14, 22, 18)
private val CORNER_RADIUS = 4.dp
private val X_PADDING = 16.dp.toInt()
private val Y_PADDING = 8.dp.toInt()

class ColorTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        setTextColor(Color.WHITE)
        gravity = Gravity.CENTER
        val random = Math.random()
        textSize = TEXT_SIZE[Random().nextInt(TEXT_SIZE.size)].toFloat()
        paint.color = COLORS[Random().nextInt(COLORS.size)]
        setPadding(X_PADDING, Y_PADDING, X_PADDING, Y_PADDING)
    }


    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(
            0f, 0f, width.toFloat(),
            height.toFloat(), CORNER_RADIUS, CORNER_RADIUS, paint
        )
        super.onDraw(canvas)
    }
}