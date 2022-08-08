package com.sws.study.utils

import android.content.res.Resources
import android.util.TypedValue

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


val Int.dp: Float
    get() = this.toFloat().dp


val Float.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )


val Int.sp: Float
    get() = this.toFloat().sp

