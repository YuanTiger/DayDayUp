package com.sws.study.customview.view

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.sws.study.R
import com.sws.study.utils.dp


private val PIC_SIZE = 150.dp
private val PIC_MARGIN_TOP = 70f.dp

class MultilineTextView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {

    private val text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sagittis blandit varius. Nulla vel suscipit neque. Nam bibendum tincidunt odio vel interdum. Vivamus eu rutrum nunc. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. In posuere aliquam tellus sed sollicitudin. Maecenas rutrum dignissim venenatis. Nullam eget erat sed lacus feugiat luctus. Sed tincidunt, tellus in facilisis convallis, purus ante faucibus leo, vitae rutrum tortor metus ut nisl. Suspendisse a metus sit amet nisl maximus accumsan. Nam ut magna nisl. Quisque consectetur aliquet sapien sit amet pharetra."


    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 20.dp
    }
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }

    private val fontMetrics = Paint.FontMetrics()


    override fun onDraw(canvas: Canvas) {
//        val staticLayout =
//            StaticLayout.Builder.obtain(text, 0, text.length, textPaint, width).build()
//        staticLayout.draw(canvas)

        canvas.drawBitmap(getAvatar(PIC_SIZE), width - PIC_SIZE, PIC_MARGIN_TOP, paint)

        paint.getFontMetrics(fontMetrics)
        val measureWidth = floatArrayOf(0f)
        var start = 0
        var count: Int
        var lineOffset = -fontMetrics.top
        while (start < text.length) {
            val widthSize = if (lineOffset + fontMetrics.bottom < PIC_MARGIN_TOP
                || lineOffset + fontMetrics.top > PIC_MARGIN_TOP + PIC_SIZE
            ) width.toFloat() else width - PIC_SIZE

            count = paint.breakText(text, start, text.length, true, widthSize, measureWidth)
            canvas.drawText(text, start, start + count, 0f, lineOffset, paint)
            start += count
            lineOffset += paint.fontSpacing
        }


    }


    private fun getAvatar(width: Float): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.mipmap.avatar, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width.toInt()
        return BitmapFactory.decodeResource(resources, R.mipmap.avatar, options)
    }
}