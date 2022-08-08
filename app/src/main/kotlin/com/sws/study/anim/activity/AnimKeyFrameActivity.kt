package com.sws.study.anim.activity

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.sws.study.R
import com.sws.study.utils.dp

/**
 * @author mengyuan
 * @date 2022/7/28/1:53 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 关键帧
 */
class AnimKeyFrameActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_key_frame)

        val view1 = findViewById<View>(R.id.view_1)

        val animDuration = 1500L
        val tranDistance = 200.dp

        /**
         * Keyframe:关键帧
         * 参数一：动画的时间进度，也就是动画播放的时间进度，0f-1f
         * 参数二：动画的播放进度，也就是动画目前进展的百分比，0f-1f、
         */
        val keyFrame1 = Keyframe.ofFloat(0f, 0f)
        val keyFrame2 = Keyframe.ofFloat(0.2f, 1.5f * tranDistance)
        val keyFrame3 = Keyframe.ofFloat(0.8f, 0.6f * tranDistance)
        val keyFrame4 = Keyframe.ofFloat(1f, tranDistance)

        val keyFrameHolder = PropertyValuesHolder.ofKeyframe(
            "translationX",
            keyFrame1,
            keyFrame2,
            keyFrame3,
            keyFrame4
        )
        val animator = ObjectAnimator.ofPropertyValuesHolder(view1, keyFrameHolder)
        animator.startDelay = 2000
        animator.duration = animDuration
        animator.start()

    }
}