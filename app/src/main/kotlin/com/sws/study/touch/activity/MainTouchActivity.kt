package com.sws.study.touch.activity

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.sws.study.R
import com.sws.study.utils.router

/**
 * @author mengyuan
 * @date 2022/8/9/2:31 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 触摸反馈学习
 */
class MainTouchActivity : ComponentActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_touch)


        findViewById<View>(R.id.bt_touch).setOnClickListener(this)
        findViewById<View>(R.id.bt_scalable_view).setOnClickListener(this)
        findViewById<View>(R.id.bt_multi_touch).setOnClickListener(this)
        findViewById<View>(R.id.bt_view_pager).setOnClickListener(this)
        findViewById<View>(R.id.bt_drag).setOnClickListener(this)

    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_touch -> {
                router(TouchActivity::class.java)
            }
            R.id.bt_scalable_view -> {
                router(ScalableImageViewActivity::class.java)
            }
            R.id.bt_multi_touch -> {
                router(MultiTouchActivity::class.java)
            }
            R.id.bt_view_pager -> {
                router(TwoViewPagerActivity::class.java)
            }
            R.id.bt_drag -> {
                router(DragActivity::class.java)
            }
        }
    }
}