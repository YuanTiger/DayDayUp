package com.sws.study.customview.drawable

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import androidx.core.graphics.toColorInt
import com.sws.study.utils.dp

/**
 * @author mengyuan
 * @date 2022/7/29/3:14 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 网格View，通过自定义Drawable实现
 */

/**
 * 网格每根线的间隔
 */
private val LINE_LIMIT = 30.dp

class MeshDrawable : Drawable() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = "#0091ff".toColorInt()
        strokeWidth = 5.dp
    }

    override fun draw(canvas: Canvas) {

        var x = bounds.left.toFloat()

        while (x <= bounds.right) {
            canvas.drawLine(
                x,
                bounds.top.toFloat(),
                x,
                bounds.bottom.toFloat(),
                paint
            )
            x += LINE_LIMIT
        }


        var y = bounds.top.toFloat()

        while (y <= bounds.bottom) {
            canvas.drawLine(
                bounds.left.toFloat(),
                y,
                bounds.right.toFloat(),
                y,
                paint
            )
            y += LINE_LIMIT
        }

    }


    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }


    override fun getAlpha(): Int {
        return paint.alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }


    override fun getColorFilter(): ColorFilter? {
        return paint.colorFilter
    }

    override fun getOpacity(): Int {
        return when (paint.alpha) {
            0 -> PixelFormat.TRANSPARENT
            0xff -> PixelFormat.OPAQUE
            else -> PixelFormat.TRANSPARENT
        }
    }
}