package com.sws.study.anim.view

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.sws.study.utils.dp

/**
 * @author mengyuan
 * @date 2022/7/28/5:46 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 */


val cityList = arrayListOf(
    "北京市1",
    "河南省1",
    "澳门特别行政区1",
    "台湾1",
    "香港特别行政区1",
    "重庆市1",
    "北京市2",
    "河南省2",
    "澳门特别行政区2",
    "台湾2",
    "香港特别行政区2",
    "重庆市2",
    "北京市3",
    "河南省3",
    "澳门特别行政区3",
    "台湾3",
    "香港特别行政区3",
    "重庆市3",
    "北京市4",
    "河南省4",
    "澳门特别行政区4",
    "台湾4",
    "香港特别行政区4",
    "重庆市4",
    "北京市",
    "河南省",
    "澳门特别行政区",
    "台湾",
    "香港特别行政区",
    "重庆市",
    "云南省",
)

class ProvinceView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) :
    View(context, attrs, defStyleAttr, defStyleRes) {


    var province = "北京市"
        set(value) {
            field = value
            invalidate()
        }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 80.dp
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText(province, width / 2f, height / 2f, paint)
    }




}