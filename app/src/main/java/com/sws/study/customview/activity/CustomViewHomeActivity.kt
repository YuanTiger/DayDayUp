package com.sws.study.customview.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.sws.study.R

/**
 * @author mengyuan
 * @date 2022/7/14/4:48 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 自定义View入口
 * 包含了自定义View的相关学习内容
 */
class CustomViewHomeActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view_home)
    }
}