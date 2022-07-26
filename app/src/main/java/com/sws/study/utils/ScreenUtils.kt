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

val Float.px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )


val Int.px: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()


