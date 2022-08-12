package com.sws.study.customview.view

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
 * 实现手指接力
 * 优先由新触碰的那根手指掌控
 */
class MultiTouchView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bitmap = getAvatar(200.dp)


    private var offsetX = 0f
    private var offsetY = 0f

    private var downX = 0f
    private var downY = 0f

    private var originalOffsetX = 0f
    private var originalOffsetY = 0f

    private var trackingPointId = 0

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                trackingPointId = event.getPointerId(0)
                downX = event.x
                downY = event.y
                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }
            //多指触碰
            MotionEvent.ACTION_POINTER_DOWN -> {
                //发生Down事件的index
                val actionIndex = event.actionIndex
                //根据index，查询出最新按下的手指ID
                trackingPointId = event.getPointerId(actionIndex)
                downX = event.getX(actionIndex)
                downY = event.getY(actionIndex)
                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }
            //多指触碰时，有手指抬起
            MotionEvent.ACTION_POINTER_UP -> {
                val actionIndex = event.actionIndex
                val pointerId = event.getPointerId(actionIndex)
                //这次抬起的手指，是我们监听的最新按下的那根手指
                if (pointerId == trackingPointId) {
                    //取出上一根手指，进行监听
                    val newIndex = if (actionIndex == event.pointerCount - 1) {
                        event.pointerCount - 2
                    } else {
                        event.pointerCount - 1
                    }
                    trackingPointId = event.getPointerId(newIndex)
                    downX = event.getX(newIndex)
                    downY = event.getY(newIndex)
                    originalOffsetX = offsetX
                    originalOffsetY = offsetY
                }

            }
            MotionEvent.ACTION_MOVE -> {
                //MOVE事件全部由我们指定的手指接管
                val index = event.findPointerIndex(trackingPointId)
                offsetX = event.getX(index) - downX + originalOffsetX
                offsetY = event.getY(index) - downY + originalOffsetY
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