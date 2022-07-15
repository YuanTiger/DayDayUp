package com.sws.study.utils

import android.content.Intent
import android.content.res.Resources
import android.util.TypedValue
import androidx.activity.ComponentActivity

/**
 * @author mengyuan
 * @date 2022/7/15/10:08 上午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 工具方法
 */

val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )


val Int.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()


