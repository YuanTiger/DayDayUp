package com.sws.study.customview.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View
import com.sws.study.customview.drawable.MeshDrawable
import com.sws.study.utils.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author mengyuan
 * @date 2022/7/29/2:58 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 *
 */
class BitmapDrawableView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

//    val drawable = ColorDrawable(Color.GREEN)
    val drawable = MeshDrawable()


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawable.setBounds(0,0, width, height)
//        drawable.setBounds(50.dp.toInt(), 50.dp.toInt(), width, height)
        drawable.draw(canvas)
    }
}