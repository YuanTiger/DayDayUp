package com.sws.study.customview.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
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

    private val paint = Paint()
    private val rectF = RectF(100F.dp, 100F.dp, 200F.dp, 200F.dp)
    private val rect = Rect(200.dp, 200.dp, 300.dp, 300.dp)
    private val path = Path()

    init {
        paint.color = ContextCompat.getColor(context, R.color.c_9a2322)
        paint.strokeWidth = 2F.dp
        path.addRect(rectF, Path.Direction.CW)
    }


    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
        //必须先设置裁剪区域
        //该区域为显示区域，该区域外的任何ui都不会显示
        canvas?.clipPath(path)
        //只能裁剪一块区域，如果裁剪两块区域，则什么都不会显示
//        canvas?.clipRect(rect)
        //画了一个500dp半径的圆
        canvas?.drawCircle(0F, 0F, 500F.dp, paint)
    }
}