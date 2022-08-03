package com.sws.study.customview.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.sws.study.R
import com.sws.study.utils.dp
import com.sws.study.utils.sp

/**
 * @author mengyuan
 * @date 2022/8/3/10:08 上午
 * @e-mail mengyuanzz@126.com
 * -----------
 * MaterialEditText
 */

private val TEXT_SIZE = 16f.dp
private val TEXT_MARGIN = 6f.dp
private val HORIZONTAL_OFFSET = 4.dp
private val VERTICAL_OFFSET = 18.dp
private val EXTRA_VERTICAL_OFFSET = 16.dp

class MaterialEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    private val paintFloat = Paint(Paint.ANTI_ALIAS_FLAG)
    private var floatingLabelIsShow = false


    /**
     * ObjectAnimator获取起点就是floatingLabelFraction的当前值
     * 需要保证floatingLabelFraction是public的，否则会获取不到，获取不到，ObjectAnimator的开始默认值就会为0
     * 当ObjectAnimator既指定了起点，也指定了终点，就可以设置为private
     */
    private var floatingLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    var useFloatingLabel = false
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    setPadding(
                        paddingLeft,
                        (paddingTop + TEXT_SIZE + TEXT_MARGIN).toInt(),
                        paddingRight,
                        paddingBottom
                    )
                } else {
                    setPadding(
                        paddingLeft,
                        (paddingTop - TEXT_SIZE - TEXT_MARGIN).toInt(),
                        paddingRight,
                        paddingBottom
                    )
                }
            }
        }

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        useFloatingLabel = typeArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true)
        typeArray.recycle()

        paintFloat.textSize = TEXT_SIZE
        paintFloat.color = Color.parseColor("#D81B60")
    }

    private val animator by lazy {
        ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0f, 1f)
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        //如果浮动标签显示 && 文本为空，隐藏浮动标签
        if (useFloatingLabel && floatingLabelIsShow && text.isNullOrEmpty()) {
            floatingLabelIsShow = false
            //反向播放动画，使用该函数必须指定ObjectAnimator的起点和终点
            animator.reverse()
        }
        //如果浮动标签隐藏 && 文本不为空，显示浮动标签
        else if (useFloatingLabel && !floatingLabelIsShow && !text.isNullOrEmpty()) {
            floatingLabelIsShow = true
            animator.start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paintFloat.alpha = (floatingLabelFraction * 0xff).toInt()
        val currentVerticalValue =
            VERTICAL_OFFSET + EXTRA_VERTICAL_OFFSET * (1f - floatingLabelFraction)

        canvas.drawText(hint.toString(), HORIZONTAL_OFFSET, currentVerticalValue, paintFloat)
    }
}