package com.sws.study.anim.activity

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.sws.study.R

/**
 * @author mengyuan
 * @date 2022/7/28/10:05 上午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 动画集合效果
 */
class AnimSetActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_set)


        val view1 = findViewById<View>(R.id.view_1)


        val animDuration = 1500L
//
//        val bottomFlipAnimator = ObjectAnimator.ofFloat(view1, "bottomFlip", 60f)
//        bottomFlipAnimator.duration = animDuration
//        bottomFlipAnimator.startDelay = 1000
//
//        val flipRotationAnimator = ObjectAnimator.ofFloat(view1, "flipRotation", 270f)
//        flipRotationAnimator.duration = animDuration
//        flipRotationAnimator.startDelay = 200
//
//        val topFlipAnimator = ObjectAnimator.ofFloat(view1, "topFlip", -60f)
//        topFlipAnimator.duration = animDuration
//        topFlipAnimator.startDelay = 200
//
//        /**
//         * AnimatorSet:按照指定顺序，有序进行动画
//         */
//        val animatorSet = AnimatorSet()
//        animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator)
//        animatorSet.start()

        /**
         * PropertyValuesHolder：实现同时播放三个动画
         */
        val bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip", 60f)
        val flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation", 270f)
        val topFlipHolder = PropertyValuesHolder.ofFloat("topFlip", -60f)

        val holderAnimator = ObjectAnimator.ofPropertyValuesHolder(
            view1,
            bottomFlipHolder,
            flipRotationHolder,
            topFlipHolder
        )
        holderAnimator.startDelay = 1000
        holderAnimator.duration = animDuration
        holderAnimator.start()


    }
}