package com.sws.study.customview.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.sws.study.R
import com.sws.study.customview.view.ScalableImageView
import com.sws.study.customview.view.TouchView

/**
 * @author mengyuan
 * @date 2022/8/8/10:44 上午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 支持双向滑动的View
 */
class ScalableImageViewActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scalable_image_view)


    }
}