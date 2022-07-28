package com.sws.study.anim.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.sws.study.R
import com.sws.study.utils.dp

/**
 * @author mengyuan
 * @date 2022/7/27/2:51 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * Canvas几何变换
 */

private val IMAGE_WIDTH = 200f.dp
private val MARGIN = 20f.dp


class CameraAnimView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    /**
     *
     */
    private val camera = Camera()

    var topFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var bottomFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var flipRotation = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        //调整z轴上摄像头的位置，负值代表离屏幕越来越远，投影成像就会越来越小
        camera.setLocation(0f, 0f, -4f * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        //注意我们操作的是Canvas，是画板
//        canvas.translate(200.dp,0f)
//        canvas.rotate(45f)
//        canvas.scale(2f,2f)

        //上半部分
        //注意阅读顺序，这些操作步骤，实际上是从下往上读的
        canvas.withSave {
            canvas.translate(width / 2f, height / 2f)
            canvas.rotate(-flipRotation)
            camera.save()
            camera.rotateX(topFlip)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.clipRect(-IMAGE_WIDTH, -IMAGE_WIDTH, IMAGE_WIDTH, 0f)
            canvas.rotate(flipRotation)
            canvas.translate(-width / 2f, -height / 2f)
            canvas.drawBitmap(
                getAvatar(IMAGE_WIDTH),
                width / 2f - IMAGE_WIDTH / 2f,
                height / 2f - IMAGE_WIDTH / 2f,
                paint
            )
        }
        //下半部分
        canvas.withSave {
            canvas.translate(width / 2f, height / 2f)
            canvas.rotate(-flipRotation)
            camera.save()
            camera.rotateX(bottomFlip)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.clipRect(-IMAGE_WIDTH, 0f, IMAGE_WIDTH, IMAGE_WIDTH)
            canvas.rotate(flipRotation)
            canvas.translate(-width / 2f, -height / 2f)
            canvas.drawBitmap(
                getAvatar(IMAGE_WIDTH),
                width / 2f - IMAGE_WIDTH / 2f,
                height / 2f - IMAGE_WIDTH / 2f,
                paint
            )
        }

    }


    private fun getAvatar(width: Float): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.mipmap.ic_avatar, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width.toInt()
        return BitmapFactory.decodeResource(resources, R.mipmap.ic_avatar, options)

    }
}