package com.sws.study.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.sws.study.R
import com.sws.study.utils.dp

private val CIRCLE_COLOR = Color.parseColor("#90A4AE")
private val HIGHLIGHT_COLOR = Color.parseColor("#FF4081")
private val RING_WIDTH = 14.dp
private val RADIUS = 100.dp

class SportView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 60.dp
        typeface = ResourcesCompat.getFont(context, R.font.font)
        textAlign = Paint.Align.CENTER
    }
    private val bounds = Rect()
    private val fontMetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 绘制环
        paint.style = Paint.Style.STROKE
        paint.color = CIRCLE_COLOR
        paint.strokeWidth = RING_WIDTH
        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)

        // 绘制进度条
        paint.color = HIGHLIGHT_COLOR
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            -90f,
            225f,
            false,
            paint
        )

        val text = "abcd"
        paint.style = Paint.Style.FILL
        //使用这种测量方式，是在测量文本内容的矩阵大小的坐标
        //这个矩阵大小是刚好包裹住文本大小的，所以说像p、g这种靠下的字母，会导致矩阵偏下
        //也就是说如果文本内容中有这些字符，会导致矩阵偏下，如果文本中没有这些字符，又会导致文本居中，从而导致动态改变文本时，文字上下跳动
//        paint.getTextBounds(text, 0, text.length, bounds)
//        canvas.drawText(text, width / 2f, height / 2f - (bounds.top + bounds.bottom) / 2f, paint)

        //使用这种测量方式，是在测量文本的五条基准线
        //这5条基准线位置是固定不变的，所以利用基准线来计算上下偏移量，可以保证动态文本不会上下跳动
        //top:文本的最高范围，最高不能超过该范围
        //ascent:文本顶部的常用范围，适用于大部分国家字体，也就是说大部分国家的字体都在这个范围内
        //baseline:文本的底部基准线
        //descent:文本底部的常用范围，适用于大部分国家字体，也就是说大部分国家的字体都在这个范围内
        //bottom:文本的最低范围，最低不能超过该范围
        paint.getFontMetrics(fontMetrics)
        canvas.drawText(
            text,
            width / 2f,
            height / 2f - (fontMetrics.ascent + fontMetrics.descent) / 2f,
            paint
        )

//        paint.textAlign = Paint.Align.LEFT
//        paint.getFontMetrics(fontMetrics)
//        //使用top是因为我们不知道会绘制什么内容
//        //如果绘制最长的字体，顶到了文本的top顶部，如果用ascent就会有一部分字体看不到了
//        canvas.drawText("qwer", 0f, -fontMetrics.top, paint)

        //使用矩阵测量，可以完美的让字体贴合顶部
//        paint.getTextBounds("uiop", 0, "uiop".length, bounds)
//        canvas.drawText("uiop", 0f, -bounds.top.toFloat(), paint)
    }
}