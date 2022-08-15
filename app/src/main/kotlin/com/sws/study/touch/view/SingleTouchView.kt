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
 * 单点触摸实例
 */
class SingleTouchView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bitmap = getAvatar(200.dp)

    /**
     * 当前偏移量
     */
    private var offsetX = 0f
    private var offsetY = 0f

    /**
     * 按下时的位置，保证从用户手指的位置进行偏移
     */
    private var downX = 0f
    private var downY = 0f

    /**
     * 上次的偏移量，当进行下一次滑动时，需要累加上次的偏移量
     */
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }
            MotionEvent.ACTION_MOVE -> {
                offsetX = event.x - downX + originalOffsetX
                offsetY = event.y - downY + originalOffsetY
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