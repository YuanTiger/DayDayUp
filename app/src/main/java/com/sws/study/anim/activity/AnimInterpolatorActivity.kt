package com.sws.study.anim.activity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.activity.ComponentActivity
import com.sws.study.R
import com.sws.study.utils.dp

/**
 * @author mengyuan
 * @date 2022/7/28/2:10 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 动画插值器
 */
class AnimInterpolatorActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_interpolator)

        val view1 = findViewById<View>(R.id.view_1)

        val animator = ObjectAnimator.ofFloat(view1, "translationX", 300.dp)
        animator.duration = 2000
        animator.startDelay = 1000
        /**
         * 先加速后减速：默认值
         */
//        animator.interpolator = AccelerateDecelerateInterpolator()
        /**
         * 越来越快
         */
//        animator.interpolator = AccelerateInterpolator()
        /**
         * 越来越慢
         */
        animator.interpolator = DecelerateInterpolator()
        /**
         * 匀速运动
         */
//        animator.interpolator = LinearInterpolator()
        animator.start()

    }
}