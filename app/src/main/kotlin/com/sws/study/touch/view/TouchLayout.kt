package com.sws.study.touch.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.children

/**
 * @author mengyuan
 * @date 2022/8/5/2:03 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 */
class TouchLayout(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        return super.onTouchEvent(event)
    }


    /**
     * Return true if the pressed state should be delayed for children or descendants of this
     * ViewGroup. Generally, this should be done for containers that can scroll, such as a List.
     * This prevents the pressed state from appearing when the user is actually trying to scroll
     * the content.
     *
     * The default implementation returns true for compatibility reasons. Subclasses that do
     * not scroll should generally override this method and return false.
     */
    override fun shouldDelayChildPressedState(): Boolean {
        return false
    }
}