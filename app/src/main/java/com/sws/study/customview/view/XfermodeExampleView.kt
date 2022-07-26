package com.sws.study.customview.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.sws.study.utils.dp

/**
 * @author mengyuan
 * @date 2022/7/26/4:27 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * Xfermode官方案例
 */

private val XFERMODE = PorterDuffXfermode(PorterDuff.Mode.SRC)

class XfermodeExampleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bounds = RectF(150f.dp, 50f.dp, 300f.dp, 200f.dp)

    override fun onDraw(canvas: Canvas) {
        val count = canvas.saveLayer(bounds, null)

        paint.color = Color.parseColor("#D81B60")
        canvas.drawOval(200f.dp, 50f.dp, 300f.dp, 150f.dp, paint)

        paint.xfermode = XFERMODE

        paint.color = Color.parseColor("#2396f3")
        canvas.drawRect(150f.dp, 100f.dp, 250f.dp, 200f.dp, paint)

        paint.xfermode = null
        canvas.restoreToCount(count)

    }


}