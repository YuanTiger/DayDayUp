package com.sws.study.anim.activity

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.sws.study.R
import com.sws.study.utils.router

/**
 * @author mengyuan
 * @date 2022/7/27/6:01 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 动画
 */
class MainAnimActivity : ComponentActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_anim)

        findViewById<View>(R.id.bt_base_anim).setOnClickListener(this)
        findViewById<View>(R.id.bt_anim_set).setOnClickListener(this)
        findViewById<View>(R.id.bt_key_frame).setOnClickListener(this)
        findViewById<View>(R.id.bt_interpolator).setOnClickListener(this)
        findViewById<View>(R.id.bt_type_evaluator).setOnClickListener(this)
        findViewById<View>(R.id.bt_layer_type).setOnClickListener(this)

    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bt_base_anim -> {
                router(AnimBaseActivity::class.java)
            }
            R.id.bt_anim_set -> {
                router(AnimSetActivity::class.java)
            }
            R.id.bt_key_frame -> {
                router(AnimKeyFrameActivity::class.java)
            }
            R.id.bt_interpolator -> {
                router(AnimInterpolatorActivity::class.java)
            }
            R.id.bt_type_evaluator -> {
                router(AnimTypeEvaluatorActivity::class.java)
            }
            R.id.bt_layer_type -> {
                router(AnimTypeEvaluatorActivity::class.java)
            }

        }
    }
}