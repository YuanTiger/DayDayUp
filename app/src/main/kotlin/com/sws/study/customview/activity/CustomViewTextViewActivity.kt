package com.sws.study.customview.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.sws.study.R

/**
 * @author mengyuan
 * @date 2022/7/27/9:41 上午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 自定义TextView
 */
class CustomViewTextViewActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_text_view)
    }
}