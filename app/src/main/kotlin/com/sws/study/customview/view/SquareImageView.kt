package com.sws.study.customview.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min


/**
 * @author mengyuan
 * @date 2022/8/3/2:41 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 方形ImageView
 */
class SquareImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //不删除super的测量逻辑，先让其走原有的测量逻辑
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //之后拿到测量结果，取出短的一边，进行修改，保证正方形
        val size = min(measuredHeight, measuredWidth)
        setMeasuredDimension(size, size)
    }


    /**
     * 使用该方式，也可以实现方形效果
     * 不过这种实现，父View并不知道子View大小发生了变化
     * 因为layout是在measure之后，执行layout时，measure已经完成了，子View大小和位置都已经被测量好了
     * 但此时我们通过重写layout，修改了子View的摆放逻辑，并且未通知父View，其他View摆放就会出现问题
     */
//    override fun layout(l: Int, t: Int, r: Int, b: Int) {
//        val width = r - l
//        val height = b - t
//        val size = min(width, height)
//        super.layout(l, t, l + size, t + size)
//    }
}