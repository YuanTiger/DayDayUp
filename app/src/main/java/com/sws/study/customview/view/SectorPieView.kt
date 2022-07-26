package com.sws.study.customview.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.sws.study.utils.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author mengyuan
 * @date 2022/7/26/10:30 上午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 饼图
 */

private val DASH_RADIUS = 120F.dp
private val OFFSET_LENGTH = 20F.dp

private val ANGLE = floatArrayOf(60f, 80f, 150f, 70f)
private val COLORS = listOf(
    Color.parseColor("#0091ff"),
    Color.parseColor("#ffcc45"),
    Color.parseColor("#5d4037"),
    Color.parseColor("#00acc1")
)

class SectorPieView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val piePaint = Paint(Paint.ANTI_ALIAS_FLAG)


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

    }

    override fun onDraw(canvas: Canvas) {
        var startAngle = 0f
        val moveIndex = 2
        ANGLE.forEachIndexed { index, angle ->
            piePaint.color = COLORS[index]
            if (index == moveIndex) {
                canvas.save()
                canvas.translate(
                    (OFFSET_LENGTH * cos(Math.toRadians(startAngle + angle / 2f.toDouble()))).toFloat(),
                    (OFFSET_LENGTH * sin(Math.toRadians(startAngle + angle / 2f.toDouble()))).toFloat()
                )
            }
            canvas.drawArc(
                width / 2f - DASH_RADIUS,
                height / 2f - DASH_RADIUS,
                width / 2f + DASH_RADIUS,
                height / 2f + DASH_RADIUS,
                startAngle,
                angle,
                true,
                piePaint
            )
            startAngle += angle
            if (index == moveIndex) {
                canvas.restore()
            }
        }


    }

}