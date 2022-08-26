package com.sws.study

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.sws.study.anim.activity.MainAnimActivity
import com.sws.study.customview.activity.MainCustomViewActivity
import com.sws.study.thread.activity.MainThreadActivity
import com.sws.study.touch.activity.MainTouchActivity
import com.sws.study.transition.activity.MainTransitionActivity
import com.sws.study.utils.router
import com.sws.study.utils.toastShow

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
        findViewById<View>(R.id.bt_touch).setOnClickListener(this)
        findViewById<View>(R.id.bt_thread).setOnClickListener(this)
        findViewById<View>(R.id.bt_transition).setOnClickListener(this)
        findViewById<View>(R.id.bt_annotation).setOnClickListener(this)

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.bt_custom_view -> {
                router(MainCustomViewActivity::class.java)
            }
            R.id.bt_anim -> {
                router(MainAnimActivity::class.java)
            }
            R.id.bt_touch -> {
                router(MainTouchActivity::class.java)
            }
            R.id.bt_thread -> {
                router(MainThreadActivity::class.java)
            }
            R.id.bt_transition -> {
                router(MainTransitionActivity::class.java)
            }
            R.id.bt_annotation -> {
                router(AnnotationTestActivity::class.java)
            }
            else -> {
                toastShow("not found")
            }
        }

    }


}
