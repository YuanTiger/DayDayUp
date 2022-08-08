package com.sws.study.customview.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.sws.study.R

/**
 * @author mengyuan
 * @date 2022/8/3/2:40 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 自定义View的测量实践
 */
class MeasureActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measure)
    }
}