package com.sws.study.transition.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import com.sws.study.R
import com.sws.study.utils.toastShow

/**
 * @author mengyuan
 * @date 2022/8/16/11:12 上午
 * @e-mail mengyuanzz@126.com
 * -----------
 * MotionLayout使用-3
 */
class MotionLayoutUse3Activity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_layout_3)

        findViewById<MotionLayout>(R.id.root).addTransitionListener(object : TransitionAdapter() {

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
                toastShow("动画进度{$progress}")
            }
        })

    }


}