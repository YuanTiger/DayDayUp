package com.sws.study.generics

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.ui.text.font.Typeface
import androidx.lifecycle.ViewModelProvider
import com.sws.study.R

/**
 * @author mengyuan
 * @date 2022/9/1/6:54 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 */
class TestViewModelActivity : BaseViewModelActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_view_model)
        findViewById<TextView>(R.id.tv_text).text = viewModel.test()
    }
}