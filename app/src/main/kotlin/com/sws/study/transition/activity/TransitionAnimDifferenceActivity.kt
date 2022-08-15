package com.sws.study.transition.activity

import android.os.Bundle
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import com.sws.study.R
import com.sws.study.utils.getScreenWidth
import com.sws.study.utils.router

/**
 * @author mengyuan
 * @date 2022/8/15/6:44 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 过渡动画与属性动画的区别
 */
class TransitionAnimDifferenceActivity : ComponentActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_anim_difference)

        findViewById<View>(R.id.iv_pic).setOnClickListener(this)

    }


    override fun onClick(view: View) {
        //属性动画：从左边移动至右边
        //不会改变View的宽高
//        val moveDistance = getScreenWidth() - view.width
//        view.animate().translationX(moveDistance.toFloat()).setDuration(1000).start()

        //过渡动画：直接修改控件属性，让我们的关注点，放到UI的需求上
        val layoutParams = view.layoutParams as FrameLayout.LayoutParams
        TransitionManager.beginDelayedTransition(view.parent as ViewGroup)
//        layoutParams.gravity = Gravity.END
        layoutParams.gravity = Gravity.CENTER
        layoutParams.width *= 2
        layoutParams.height *= 2
        view.requestLayout()


    }


}