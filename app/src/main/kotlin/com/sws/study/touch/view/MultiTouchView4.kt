package com.sws.study.touch.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import com.sws.study.utils.dp

/**
 * @author mengyuan
 * @date 2022/8/12/3:20 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 多点触摸实例-绘画板
 * 各自为战型
 * 每根手指各管各的
 */
class MultiTouchView4(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // TODO: 可优化，不用每次都创建新的Path
    private var paths = SparseArray<Path>()

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4.dp
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
    }


    override fun onDraw(canvas: Canvas) {
        for (i in 0 until paths.size()) {
            val path = paths.valueAt(i)
            canvas.drawPath(path, paint)
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val actionIndex = event.actionIndex
                val path = Path()
                path.moveTo(event.getX(actionIndex), event.getY(actionIndex))
                paths.append(event.getPointerId(actionIndex), path)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                for (index in 0 until event.pointerCount) {
                    val path = paths.get(event.getPointerId(index))
                    path.lineTo(event.getX(index), event.getY(index))
                }
                invalidate()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                val pointerId = event.getPointerId(event.actionIndex)
                val path = paths.get(pointerId)
                path.reset()
                paths.remove(pointerId)
                invalidate()
            }
        }

        return true
    }
}