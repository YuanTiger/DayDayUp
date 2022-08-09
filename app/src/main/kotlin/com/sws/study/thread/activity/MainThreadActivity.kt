package com.sws.study.thread.activity

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
 * 线程学习
 */
class MainThreadActivity : ComponentActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_thread)

        findViewById<View>(R.id.bt_thread_base).setOnClickListener(this)

    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_thread_base -> {
                router(ThreadBaseActivity::class.java)
            }
        }
    }
}