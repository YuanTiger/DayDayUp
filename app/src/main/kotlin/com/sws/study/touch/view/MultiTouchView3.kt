package com.sws.study.touch.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.sws.study.R
import com.sws.study.utils.dp

/**
 * @author mengyuan
 * @date 2022/8/12/11:26 上午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 多点触摸实例
 * 实现多指控制(相互协作型)
 * 也就是每根手指，都在起作用
 */
class MultiTouchView3(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bitmap = getAvatar(200.dp)


    private var offsetX = 0f
    private var offsetY = 0f

    private var downX = 0f
    private var downY = 0f

    private var originalOffsetX = 0f
    private var originalOffsetY = 0f

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        //计算多根手指的中心点，也就是平均值
        var pointCount = event.pointerCount
        var sumX = 0f
        var sumY = 0f
        //当发生手指抬起事件时，因为抬起事件是描述抬起的那根手指的
        //所以此时，pointerCount仍然是抬起前的手指数量
        //但此时我们不应该再关心抬起的那根手指，所以此处进行筛除
        val isUp = event.actionMasked == MotionEvent.ACTION_POINTER_UP
        for (index in 0 until pointCount) {
            if (!(isUp && event.actionIndex == index)) {
                sumX += event.getX(index)
                sumY += event.getY(index)
            }
        }
        if (isUp) {
            pointCount--
        }
        val focusX: Float = sumX / pointCount.toFloat()
        val focusY: Float = sumY / pointCount.toFloat()

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP -> {
                downX = focusX
                downY = focusY
                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }
            MotionEvent.ACTION_MOVE -> {
                offsetX = focusX - downX + originalOffsetX
                offsetY = focusY - downY + originalOffsetY
                invalidate()
            }
        }
        return true
    }

    private fun getAvatar(width: Float): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.mipmap.ic_avatar, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width.toInt()
        return BitmapFactory.decodeResource(resources, R.mipmap.ic_avatar, options)
    }
}