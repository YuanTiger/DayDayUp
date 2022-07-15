package com.sws.study.customview.activity

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.sws.study.R
import com.sws.study.utils.router

/**
 * @author mengyuan
 * @date 2022/7/14/4:48 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 自定义View入口
 * 包含了自定义View的相关学习内容
 */
class CustomViewHomeActivity : ComponentActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view_home)

        findViewById<View>(R.id.bt_clip).setOnClickListener(this)
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bt_clip -> {
                router(ClipHomeActivity::class.java)
            }
        }
    }
}