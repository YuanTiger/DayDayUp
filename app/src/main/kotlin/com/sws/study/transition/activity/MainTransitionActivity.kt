package com.sws.study.transition.activity

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.sws.study.R
import com.sws.study.utils.router

/**
 * @author mengyuan
 * @date 2022/8/15/6:37 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 过渡动画
 */
class MainTransitionActivity : ComponentActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_transition)

        findViewById<View>(R.id.bt_anim_difference).setOnClickListener(this)
        findViewById<View>(R.id.bt_motion_layout_1).setOnClickListener(this)
        findViewById<View>(R.id.bt_motion_layout_2).setOnClickListener(this)
        findViewById<View>(R.id.bt_motion_layout_3).setOnClickListener(this)
        findViewById<View>(R.id.bt_motion_layout_4).setOnClickListener(this)

    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_anim_difference -> {
                router(TransitionAnimDifferenceActivity::class.java)
            }
            R.id.bt_motion_layout_1 -> {
                router(MotionLayoutUse1Activity::class.java)
            }
            R.id.bt_motion_layout_2 -> {
                router(MotionLayoutUse2Activity::class.java)
            }
            R.id.bt_motion_layout_3 -> {
                router(MotionLayoutUse3Activity::class.java)
            }
            R.id.bt_motion_layout_4 -> {
                router(MotionLayoutUse4Activity::class.java)
            }
        }
    }
}