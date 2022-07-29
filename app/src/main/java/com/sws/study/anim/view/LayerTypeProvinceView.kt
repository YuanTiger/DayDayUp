package com.sws.study.anim.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.sws.study.utils.dp

/**
 * @author mengyuan
 * @date 2022/7/28/5:46 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 */

class LayerTypeProvinceView @JvmOverloads constructor(
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

    init {
        //主要用途：
        //1、可以用LAYER_TYPE_SOFTWARE来关闭View的硬件加速
        //2、可以用LAYER_TYPE_HARDWARE(anim().withLayer())来实现基础属性的动画加速(参考AnimBaseActivity中的动画)
        /**
         * 开启View的离屏缓冲，并使用GPU绘制(硬件绘制)
         */
        setLayerType(LAYER_TYPE_HARDWARE, null)
        /**
         * 开启View的离屏缓冲，并使用CPU进行绘制(软件绘制)
         */
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        /**
         * 关闭View的离屏缓冲
         */
        setLayerType(LAYER_TYPE_NONE, null)

    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 30.dp
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText(province, width / 2f, height / 2f, paint)
    }


}