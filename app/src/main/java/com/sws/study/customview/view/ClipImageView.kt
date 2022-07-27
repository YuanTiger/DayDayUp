package com.sws.study.customview.view

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.sws.study.R
import com.sws.study.utils.dp

/**
 * @author mengyuan
 * @date 2022/7/15/9:43 上午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 裁剪ImageView
 */
class ClipImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    companion object {
        const val TAG = "ClipImageView_"
    }

    private val paint = Paint()
    private val rectF = RectF(100F.dp, 100F.dp, 200F.dp, 300F.dp)
    private val path = Path()

    /**
     * 矩阵区域，X轴半径，用于移动时计算左上角坐标
     */
    private var rectFRadiusX = 0F

    /**
     * 矩阵区域，Y轴半径，用于移动时计算左上角坐标
     */
    private var rectFRadiusY = 0F

    /**
     * 是否处于移动模式
     */
    private var isMove = false

    init {
        paint.color = ContextCompat.getColor(context, R.color.c_80000000)
        paint.strokeWidth = 2F.dp
        paint.isAntiAlias = true
        path.addRect(rectF, Path.Direction.CW)
        rectFRadiusX = (rectF.right - rectF.left) / 2
        Log.i(TAG, "rectFRadiusX:${rectFRadiusX}")
        rectFRadiusY = (rectF.bottom - rectF.top) / 2
        Log.i(TAG, "rectFRadiusY:${rectFRadiusY}")

    }


    /**
     * 自定义绘制内容
     * 注意：页面的刷新，会导致页面上的所有组件，都重新绘制
     * 所以我们可能无法控制onDraw()的调用频率
     * 所以我们不可以在onDraw函数中，创建任何对象，否则会存在隐患
     */
    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
        //必须先设置裁剪区域
        //该区域为显示区域，该区域外的任何ui都不会显示
//        canvas?.clipPath(path)
        //只能裁剪一块区域，如果裁剪两块区域，则什么都不会显示
//        canvas?.clipRect(rectF)
        //裁剪+反选
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            canvas?.clipOutRect(rectF)
        }

        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                //如果点击的落点在矩阵区域内
                val downX = event.x.toInt()
                val downY = event.y.toInt()
                if ((downX > rectF.left && downX < rectF.right) &&
                    (downY > rectF.top && downY < rectF.bottom)
                ) {
                    Log.i(TAG, "落点在矩阵内")
                    isMove = true
                } else {
                    Log.i(TAG, "落点不在矩阵内")
                }
                return true

            }
            MotionEvent.ACTION_MOVE -> {
                if (isMove) {
                    Log.i(TAG, "MoveEvent-event.x:${event.x}---event.y:${event.x}")
                    rectF.left = event.x - rectFRadiusX
                    rectF.top = event.y - rectFRadiusY
                    rectF.right = event.x + rectFRadiusX
                    rectF.bottom = event.y + rectFRadiusY
                    //触发重新绘制
                    invalidate()
                }
                return true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                Log.i(TAG, "结束Move,ACTION:${event.action}")
                isMove = false
                return true
            }
        }
        return super.onTouchEvent(event)
    }
}