package com.sws.study.anim.activity

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import com.sws.study.R
import com.sws.study.anim.view.cityList
import com.sws.study.utils.dp

/**
 * @author mengyuan
 * @date 2022/7/28/2:56 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * TypeEvaluator的使用
 */
class AnimTypeEvaluatorActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_type_evaluator)

        val view1 = findViewById<View>(R.id.view_1)
        val view2 = findViewById<View>(R.id.view_2)

        val animator =
            ObjectAnimator.ofObject(view1, "point", PointEvaluator(), PointF(100.dp, 200.dp))
        animator.startDelay = 1000
        animator.duration = 2000
        animator.start()


        val animator2 =
            ObjectAnimator.ofObject(view2, "province", ProvinceEvaluator(), "云南省")
        animator2.startDelay = 1000
        animator2.duration = 1000 * 10
        animator2.start()


    }


    class PointEvaluator : TypeEvaluator<PointF> {

        override fun evaluate(fraction: Float, startValue: PointF?, endValue: PointF?): PointF {
            val startX = startValue?.x ?: 0f
            val startY = startValue?.y ?: 0f
            val endX = endValue?.x ?: 0f
            val endY = endValue?.y ?: 0f

            val currentX = startX + (endX - startX) * fraction
            val currentY = startY + (endY - startY) * fraction

            return PointF(currentX, currentY)
        }
    }


    class ProvinceEvaluator : TypeEvaluator<String> {

        override fun evaluate(fraction: Float, startValue: String, endValue: String): String {
            val startIndex = cityList.indexOf(startValue)
            Log.i("ProvinceEvaluator_","fraction:{$fraction}")
            Log.i("ProvinceEvaluator_","startValue:{$startValue}")
            Log.i("ProvinceEvaluator_","endValue:{$endValue}")
            Log.i("ProvinceEvaluator_","----------------------")
            val endIndex = cityList.indexOf(endValue)
            val currentIndex = startIndex + ((endIndex - startIndex) * fraction).toInt()
            return cityList[currentIndex]
        }
    }
}