package com.sws.study.anim.activity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.sws.study.R
import com.sws.study.utils.dp

/**
 * @author mengyuan
 * @date 2022/7/27/6:06 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 动画基础效果
 */
class AnimBaseActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_base)


        val view1 = findViewById<View>(R.id.oval_view_1)
        val view2 = findViewById<View>(R.id.oval_view_2)
        val view3 = findViewById<View>(R.id.oval_view_3)
        val view4 = findViewById<View>(R.id.oval_view_4)

        view1.postDelayed({
            view1.animate().translationX(200.dp).rotation(100f).setDuration(500).start()
            view2.animate().translationX(200.dp).scaleX(0.5f).scaleY(0.5f).setDuration(500).start()

            view3.animate().translationX(200.dp).scaleX(1.5f).scaleY(1.5f).alpha(0.5f)
                .setDuration(500).start()

            val animator4 = ObjectAnimator.ofFloat(view4, "radius", 150.dp)
            animator4.duration = 2000
            animator4.start()
        }, 1000)
    }
}