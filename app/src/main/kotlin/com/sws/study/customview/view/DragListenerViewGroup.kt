package com.sws.study.customview.view

import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

/**
 * @author mengyuan
 * @date 2022/8/15/10:25 上午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 拖拽View，使用DragListener实现
 * 1、拖拽时，原View仍会存在，当然可以手动控制隐藏；
 * 2、拖拽时，拖拽的View会进入半透明状态；
 * 3、拖拽时，拖拽View不会被任何View遮盖；
 */

private const val COLUMNS = 2
private const val ROUWS = 3

class DragListenerViewGroup(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private var dragListener: OnDragListener = MyDragListener()
    private var dragView: View? = null
    private var orderedChildren: MutableList<View> = ArrayList()

    init {
        isChildrenDrawingOrderEnabled = true
    }


    override fun onFinishInflate() {
        super.onFinishInflate()
        //为每个View的长按事件，注册拖拽监听
        //DragListener注册在View的点击事件中是不可以的，只能注册在长按事件中
        for (child in children) {
            orderedChildren.add(child)
            child.setOnLongClickListener {
                dragView = it
                it.startDrag(null, DragShadowBuilder(it), it, 0)
                false
            }
            //拖拽任一View时，其他所有View也都会触发回调
            child.setOnDragListener(dragListener)
        }
    }

    /**
     * 这样测量View大小并不严谨，只是为了实现效果而已
     */
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
            //全部摆放至左上角，之后再进行偏移
            child.layout(0, 0, childWidth, childHeight)
            child.translationX = childLeft.toFloat()
            child.translationY = childTop.toFloat()
        }

    }


    private inner class MyDragListener : OnDragListener {

        /**
         * 注意：拖拽任一View，所有View的拖拽回调均会触发
         */
        override fun onDrag(view: View, event: DragEvent): Boolean {
            when (event.action) {
                //拖拽开始，表示有View被拖拽起来了
                DragEvent.ACTION_DRAG_STARTED -> {
                    if (event.localState === view) {
                        view.visibility = View.INVISIBLE
                    }
                }
                //拖拽的View进入了其他View的范围边界
                //也就是需要进行重新排列了
                //如果是进入自己的View边界，则不需要重排，所以是反选
                DragEvent.ACTION_DRAG_ENTERED -> {
                    if (event.localState !== view) {
                        sort(view)
                    }
                }
                DragEvent.ACTION_DRAG_EXITED->{

                }
                //拖拽结束
                DragEvent.ACTION_DRAG_ENDED -> {
                    if (event.localState === view) {
                        view.visibility = VISIBLE
                    }
                }
            }
            return true
        }
    }

    /**
     * 自己实现排序算法
     * 偷懒写法
     */
    private fun sort(targetView: View) {
        var draggedIndex = -1
        var targetIndex = -1
        //遍历查询出【view目标的位置下标】以及【view初始的位置下标】
        for ((index, child) in orderedChildren.withIndex()) {
            if (targetView === child) {
                targetIndex = index
            } else if (dragView === child) {
                draggedIndex = index
            }
        }
        //修改view数组
        orderedChildren.removeAt(draggedIndex)
        orderedChildren.add(targetIndex, dragView!!)

        //所有View直接全部从左上角，进行偏移动画
        var childLeft: Int
        var childTop: Int
        val childWidth = width / COLUMNS
        val childHeight = height / ROUWS
        for ((index, child) in orderedChildren.withIndex()) {
            childLeft = index % 2 * childWidth
            childTop = index / 2 * childHeight
            child.animate()
                .translationX(childLeft.toFloat())
                .translationY(childTop.toFloat())
                .setDuration(150)
        }

    }

}