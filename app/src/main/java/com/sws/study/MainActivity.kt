package com.sws.study

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.sws.study.anim.activity.MainAnimActivity
import com.sws.study.customview.activity.MainCustomViewActivity
import com.sws.study.utils.router

/**
 * @author mengyuan
 * @date 2022/7/14/4:54 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 应用入口
 */
class MainActivity : ComponentActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.bt_custom_view).setOnClickListener(this)
        findViewById<View>(R.id.bt_anim).setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        val id = view?.id ?: 0
        //自定义View
        if (id == R.id.bt_custom_view) {
            router(MainCustomViewActivity::class.java)
        } else if (id == R.id.bt_anim) {
            router(MainAnimActivity::class.java)
        }
    }


}
