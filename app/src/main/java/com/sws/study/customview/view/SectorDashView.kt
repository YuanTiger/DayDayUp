package com.sws.study.customview.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.sws.study.utils.px
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author mengyuan
 * @date 2022/7/26/3:34 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 仪表盘
 */
private val DASH_RADIUS = 100F.px
private val POINT_LENGTH = 80F.px
private val DASH_WIDTH = 2F.px
private val DASH_HEIGHT = 10F.px
private const val OPEN_ANGLE = 120

class SectorDashView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val dashPointPth = Path().apply {
        addRect(0f, 0f, DASH_WIDTH, DASH_HEIGHT, Path.Direction.CCW)
    }
    private val dashPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val dashArcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 3F.px
        style = Paint.Style.STROKE
    }
    private var dashPath = Path()

    /**
     * Path测量工具，既然要测量Path，初始化放到Path固定之后
     */
    private lateinit var dashPathMeasure: PathMeasure

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        dashPath.reset()
        dashPath.addArc(
            width / 2f - DASH_RADIUS,
            height / 2f - DASH_RADIUS,
            width / 2f + DASH_RADIUS,
            height / 2f + DASH_RADIUS,
            90F + OPEN_ANGLE / 2F,
            360F - OPEN_ANGLE
        )
        dashPathMeasure = PathMeasure(dashPath, false)
        val limitLength = (dashPathMeasure.length - DASH_WIDTH) / 20f
        dashPaint.pathEffect =
            PathDashPathEffect(dashPointPth, limitLength, 0f, PathDashPathEffect.Style.ROTATE)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(dashPath, dashArcPaint)
        canvas.drawPath(dashPath, dashPaint)
        canvas.drawLine(
            width / 2f,
            height / 2f,
            width / 2f + POINT_LENGTH * cos(markToRadius(12)),
            height / 2f + POINT_LENGTH * sin(markToRadius(12)),
            dashArcPaint
        )


    }

    private fun markToRadius(mark: Int): Float =
        Math.toRadians(((90F + OPEN_ANGLE / 2F + (360F - OPEN_ANGLE) / 20F * mark).toDouble()))
            .toFloat()
}