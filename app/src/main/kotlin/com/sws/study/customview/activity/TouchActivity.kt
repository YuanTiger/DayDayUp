package com.sws.study.customview.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.sws.study.R
import com.sws.study.customview.view.TouchView

/**
 * @author mengyuan
 * @date 2022/8/5/2:02 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 触摸反馈
 */
class TouchActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch)

        findViewById<TouchView>(R.id.view_touch).setOnClickListener {

        }
    }
}