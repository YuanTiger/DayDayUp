package com.sws.study.customview.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.sws.study.R
import com.sws.study.utils.px

/**
 * @author mengyuan
 * @date 2022/7/26/4:27 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * Xfermode实战，实现圆形头像
 */

private val IMAGE_WIDTH = 200f.px
private val MARGIN = 20f.px
private val STROKE_WIDTH = 5f.px

private val XFERMODE = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

class XfermodeAvatarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = STROKE_WIDTH
        style = Paint.Style.STROKE
        color = Color.parseColor("#242323")
    }

    private val bounds = RectF(MARGIN, MARGIN, MARGIN + IMAGE_WIDTH, MARGIN + IMAGE_WIDTH)
    private val strokeBounds = RectF(
        MARGIN + STROKE_WIDTH/2,
        MARGIN + STROKE_WIDTH/2,
        MARGIN + IMAGE_WIDTH - STROKE_WIDTH/2,
        MARGIN + IMAGE_WIDTH - STROKE_WIDTH/2
    )

    override fun onDraw(canvas: Canvas) {
        val count = canvas.saveLayer(bounds, null)
        canvas.drawOval(bounds, paint)
        paint.xfermode = XFERMODE
        strokePaint.xfermode = XFERMODE
        canvas.drawBitmap(getAvatar(IMAGE_WIDTH), MARGIN, MARGIN, paint)
        canvas.drawOval(strokeBounds, strokePaint)
        paint.xfermode = null
        strokePaint.xfermode = null

        canvas.restoreToCount(count)
    }


    private fun getAvatar(width: Float): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.mipmap.avatar, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width.toInt()
        return BitmapFactory.decodeResource(resources, R.mipmap.avatar, options)

    }
}