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
 * 实现最新手指接管事件
 */
class MultiTouchView1(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
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

    private var trackingPointId = 0

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }


    /**
     * event.x：手指的x坐标
     * event.y：手指的y坐标
     * event.actionIndex：发生该行为的手指index，只用于明确事件的DOWN、UP；不可用于MOVE
     * event.getPointId：获取手指的ID
     * event.getPointIndex:获取手指的下标，该下标会一直改变
     *      比如第一根手指按下，index=0，第二根手指按下，index=1
     *      此时第一根手指抬起，第二根手指的index就会变成0
     *      但是此时，第一根手指再次按下，第一根手指的index又会变成0，第二根手指还原为1
     *      所以该字段，不可用于判断是哪根手指，只是用来遍历屏幕上的手指队列(坐标队列)
     */
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
            MotionEvent.ACTION_MOVE -> {
                //MOVE事件全部由新一根手指接管
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