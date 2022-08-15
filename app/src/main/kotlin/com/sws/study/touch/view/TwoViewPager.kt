package com.sws.study.touch.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.widget.OverScroller
import kotlin.math.abs

/**
 * @author mengyuan
 * @date 2022/8/12/5:10 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 支持左右滑动的ViewGroup
 * 仅支持2页
 */
class TwoViewPager(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private val TAG = "TwoViewPager_"


    private var downX = 0f
    private var downY = 0f
    private var downScrollX = 0f


    private val overScroller = OverScroller(context)

    /**
     * 页面是否处于滑动状态
     */
    private var scrolling = false

    /**
     * ViewConfiguration：包含了View的许多常量
     * 使用这些数值，可以使我们的自定View，更符合规范
     */
    private val viewConfiguration = ViewConfiguration.get(context)
    private var minVelocity = viewConfiguration.scaledMinimumFlingVelocity
    private var maxVelocity = viewConfiguration.scaledMaximumFlingVelocity

    /**
     * 开始滑动的阈值，滑动距离超过此值，代表用户是滑动操作
     */
    private var pagingSlop = viewConfiguration.scaledPagingTouchSlop
    private val velocityTracker = VelocityTracker.obtain()

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        measureChildren(widthMeasureSpec, heightMeasureSpec)
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//    }

    /**
     * 虽然结果完全不同于我们写的Layout文件的规则
     * 但这是我们的测试View，只是用来展示效果的
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        val childTop = 0
        var childRight = width
        val childBottom = height
        for (i in 0 until childCount) {
            getChildAt(i).layout(childLeft, childTop, childRight, childBottom)
            childLeft += width
            childRight += width
        }
    }


    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        //事件起点：重置速度计算器
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(event)
        var result = false
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                Log.i(TAG, "onInterceptTouchEvent-MotionEvent.ACTION_DOWN")
                scrolling = false
                downX = event.x
                downY = event.y
                downScrollX = scrollX.toFloat()
            }
            MotionEvent.ACTION_MOVE -> {
                Log.i(TAG, "onInterceptTouchEvent-MotionEvent.ACTION_MOVE")
                //如果已经处于滑动状态，无需拦截
                if (!scrolling) {
                    val moveDistanceX = downX - event.x
                    if (abs(moveDistanceX) > pagingSlop) {
                        scrolling = true
                        //拦截子View事件
                        result = true
                        //同时向上通知父View，不要拦截我
                        parent.requestDisallowInterceptTouchEvent(true)
                    }

                }
            }
        }
        return result
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        //事件起点：重置速度计算器
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        //将所有事件都传递给速度计算器，让它随时可以计算速度
        velocityTracker.addMovement(event)
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                Log.i(TAG, "onTouchEvent-MotionEvent.ACTION_DOWN")
                downX = event.x
                downY = event.y
                downScrollX = scrollX.toFloat()
            }
            MotionEvent.ACTION_MOVE -> {
                Log.i(TAG, "onTouchEvent-MotionEvent.ACTION_MOVE")
                //注意：滑动距离我们的认知是反着的
                val dx = (downX - event.x + downScrollX).toInt()
                    .coerceAtLeast(0)
                    .coerceAtMost(width)
                scrollTo(dx, 0)
            }
            MotionEvent.ACTION_UP -> {
                Log.i(TAG, "onTouchEvent-MotionEvent.ACTION_UP")
                //计算1000毫秒内的位移速度-》xx/s
                //最大的速度不会超过maxVelocity，如果超过，则按照maxVelocity处理
                //maxVelocity是为了保证用户体验，速度太快用户肉眼看过去不美观
                velocityTracker.computeCurrentVelocity(1000, maxVelocity.toFloat())
                //拿到速度计算结果
                val xVelocity = velocityTracker.xVelocity
                val upScrollX = scrollX
                val targetPage =
                    //如果速度<最小速度，则计算用户滑动距离是否有半个屏幕
                    if (abs(xVelocity) < minVelocity) {
                        if (upScrollX > width / 2) 1 else 0
                    } else {
                        if (xVelocity < 0) 1 else 0
                    }
                val scrollDistance = if (targetPage == 1) width - upScrollX else -upScrollX
                overScroller.startScroll(scrollX, 0, scrollDistance, 0)
                postInvalidateOnAnimation()
            }
        }
        return true
    }

    override fun computeScroll() {
        if (overScroller.computeScrollOffset()) {
            scrollTo(overScroller.currX, overScroller.currY)
            postInvalidateOnAnimation()
        }
    }
}