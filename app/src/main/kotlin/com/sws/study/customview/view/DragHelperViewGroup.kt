package com.sws.study.customview.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.customview.widget.ViewDragHelper

/**
 * @author mengyuan
 * @date 2022/8/15/10:25 上午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 拖拽View，使用ViewDragHelper实现
 */

private const val COLUMNS = 2
private const val ROUWS = 3

class DragHelperViewGroup(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private val dragHelper = ViewDragHelper.create(this, MyDragCallback())


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val specWidth = MeasureSpec.getSize(widthMeasureSpec)
        val specHeight = MeasureSpec.getSize(heightMeasureSpec)
        val childWidth = specWidth / COLUMNS
        val childHeight = specHeight / ROUWS
        measureChildren(
            MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY)
        )
        setMeasuredDimension(specWidth, specHeight)
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft: Int
        var childTop: Int
        val childWidth = width / COLUMNS
        val childHeight = height / ROUWS
        for ((index, child) in children.withIndex()) {
            childLeft = index % 2 * childWidth
            childTop = index / 2 * childHeight
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight)
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragHelper.processTouchEvent(event)
        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return dragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun computeScroll() {
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    inner class MyDragCallback : ViewDragHelper.Callback() {
        private var capturedLeft = 0f
        private var capturedTop = 0f

        /**
         * 你的手是不是想要把这个View拖拽起来
         * 返回true代表是想要进行拖拽
         */
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }


        /**
         * 当发生拖拽偏移时，是否要再次修正偏移距离
         * 默认返回0，也就是不偏移
         * 改为返回left，代表我们不进行修正，用户拖拽多少，就偏移多少
         */
        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left
        }


        /**
         * 当发生拖拽偏移时，是否要再次修正偏移距离
         * 默认返回0，也就是不偏移
         * 改为返回top，代表我们不进行修正，用户拖拽多少，就偏移多少
         */
        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        /**
         *  当View被拽起时调用，也就是拖拽的开始
         *  可以控制拖拽View的层级，记录拖拽的起点
         */
        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            capturedChild.elevation = elevation + 1
            capturedLeft = capturedChild.left.toFloat()
            capturedTop = capturedChild.top.toFloat()
        }

        /**
         * 进行View的重排
         */
        override fun onViewPositionChanged(
            changedView: View,
            left: Int,
            top: Int,
            dx: Int,
            dy: Int
        ) {
            super.onViewPositionChanged(changedView, left, top, dx, dy)
        }

        /**
         * 当拖拽结束时调用
         */
        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            dragHelper.settleCapturedViewAt(capturedLeft.toInt(), capturedTop.toInt())
            postInvalidateOnAnimation()
        }
    }

}