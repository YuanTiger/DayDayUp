package com.sws.study.customview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.icu.util.Measure
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.sws.study.utils.dp
import kotlin.math.max

/**
 * @author mengyuan
 * @date 2022/8/3/3:30 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 流式布局
 */
class TagView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {


    private val childBounds = mutableListOf<Rect>()


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //ViewGroup的宽度，也就是换行之后，ViewGroup的宽度应该还是依据最宽的那一行
        var widthUsed = 0
        //已使用的高度
        var heightUsed = 0
        //当前行已使用的宽度
        var lineWidthUsed = 0
        //当前当中最高的条目高度
        var lineMaxHeight = 0
        val widthSpaceModel = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec)
        children.forEachIndexed { index, child ->
            //测量子View的大小
            measureChildWithMargins(
                child,
                widthMeasureSpec,
                0,
                heightMeasureSpec,
                heightUsed
            )
            //如果已用宽度+该子View的宽度 > 父View宽度
            //需要折行
            //widthSpaceModel != MeasureSpec.UNSPECIFIED:如果父View宽度不限制，则不需要折行
            if (widthSpaceModel != MeasureSpec.UNSPECIFIED &&
                lineWidthUsed + child.measuredWidth > widthSpaceSize
            ) {
                heightUsed += lineMaxHeight
                lineWidthUsed = 0
                lineMaxHeight = 0
                measureChildWithMargins(
                    child,
                    widthMeasureSpec,
                    0,
                    heightMeasureSpec,
                    heightUsed
                )
            }
            //防止bounds数量不足
            if (index >= childBounds.size) {
                childBounds.add(Rect())
            }
            //指定子View的摆放位置以及大小
            val childBounds = childBounds[index]
            childBounds.set(
                lineWidthUsed,
                heightUsed,
                lineWidthUsed + child.measuredWidth,
                heightUsed + child.measuredHeight
            )

            lineWidthUsed += child.measuredWidth
            widthUsed = max(widthUsed, lineWidthUsed)
            lineMaxHeight = max(lineMaxHeight, child.measuredHeight)

        }
        val selfWidth = widthUsed
        val selfHeight = heightUsed + lineMaxHeight
        setMeasuredDimension(selfWidth, selfHeight)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        children.forEachIndexed { index, view ->
            val bounds = childBounds[index]
            view.layout(bounds.left, bounds.top, bounds.right, bounds.bottom)
        }
    }


    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }


    fun selfMeasureChildWithMargins() {

        /**
         * 下面这些代码，被封装进了#measureChildWithMargins()函数中
         */
//            //拿到本ViewGroup的限制
//            val widthSpaceModel = MeasureSpec.getMode(widthMeasureSpec)
//            val widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec)
//            //拿到子View的限制
//            val layoutParams = child.layoutParams
//            var childWidthSpaceModel = 0
//            var childWidthSpaceSize = 0
//            when (layoutParams.width) {
//                LayoutParams.MATCH_PARENT -> {
//                    when (widthSpaceModel) {
//                        /**
//                         *  MeasureSpec.EXACTLY：精确值-配合子View的MATCH_PARENT，直接使用所有可用空间
//                         *  MeasureSpec.AT_MOST：父View允许子View使用父View的最大值-配合子View的MATCH_PARENT，直接使用所有可用空间
//                         *  MeasureSpec.UNSPECIFIED：父View对子View无限制-配合子View的MATCH_PARENT，无法测量
//                         */
//                        MeasureSpec.EXACTLY, MeasureSpec.AT_MOST -> {
//                            childWidthSpaceModel = MeasureSpec.EXACTLY
//                            childWidthSpaceSize = widthSpaceSize - widthUsed
//                        }
//                        MeasureSpec.UNSPECIFIED -> {
//                            childWidthSpaceModel = MeasureSpec.UNSPECIFIED
//                            childWidthSpaceSize = 0
//                        }
//                    }
//                }
//                LayoutParams.WRAP_CONTENT -> {
//                    when (widthSpaceModel) {
//                        /**
//                         *  MeasureSpec.EXACTLY：精确值-配合子View的WRAP_CONTENT，子View的模式则确定为AT_MOST，可用空间为父View的可用空间
//                         *  MeasureSpec.AT_MOST：父View允许子View使用父View的最大值-配合子View的WRAP_CONTENT，子View的模式则确定为AT_MOST，可用空间为父View的可用空间
//                         *  MeasureSpec.UNSPECIFIED：父View对子View无限制-配合子View的WRAP_CONTENT，无法测量
//                         */
//                        MeasureSpec.EXACTLY, MeasureSpec.AT_MOST -> {
//                            childWidthSpaceModel = MeasureSpec.AT_MOST
//                            childWidthSpaceSize = widthSpaceSize - widthUsed
//                        }
//                        MeasureSpec.UNSPECIFIED -> {
//                            childWidthSpaceModel = MeasureSpec.UNSPECIFIED
//                            childWidthSpaceSize = 0
//                        }
//                    }
//                }
//                //开发者对子View大小有精确要求
//                else -> {
//                    childWidthSpaceModel = MeasureSpec.EXACTLY
//                    childWidthSpaceSize = layoutParams.width
//                }
//            }
    }
}


