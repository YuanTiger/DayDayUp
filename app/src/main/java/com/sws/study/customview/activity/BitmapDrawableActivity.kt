package com.sws.study.customview.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import com.sws.study.R

/**
 * @author mengyuan
 * @date 2022/7/29/2:38 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * Bitmap与Drawable说明
 */
class BitmapDrawableActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_view)

        /**
         * Bitmap：存放了图片每一个像素的颜色信息，是一个容器对象
         */

        /**
         * Drawable：本质上，Drawable是调用Canvas绘制的上层绘制工具，更像是一个只支持onDraw()的View
         */
//        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.avatar)
//        //KTX封装了bitmap与drawable互转的扩展函数
//        val toDrawable = bitmap.toDrawable(resources)
//        toDrawable.toBitmap()
    }
}