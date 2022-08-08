package com.sws.study.customview.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.sws.study.R
import com.sws.study.utils.dp
import kotlin.math.max
import kotlin.math.min

/**
 * @author mengyuan
 * @date 2022/8/8/10:45 上午
 * @e-mail mengyuanzz@126.com
 * -----------
 */
private val IMAGE_SIZE = 300.dp

private const val EXTRA_SCALE_FACTOR = 1.5F

class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bitmap = getAvatar()

    /**
     * 初始偏移值
     */
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f

    /**
     * 额外偏移值
     */
    private var offsetX = 0f
    private var offsetY = 0f

    /**
     *  默认缩放比率
     */
    private var smallScale = 0f

    /**
     * 大图缩放比率
     */
    private var bigScale = 0f

    /**
     * 是否正在使用大图模式
     */
    private var isUseBig = false


    /**
     * 手指操作监听
     */
    private val gestureListener = HenCoderGestureListener()
    private val gestureDetector = GestureDetectorCompat(context, gestureListener)


    /**
     * 双手指操作缩放监听
     */
    private val scaleGestureListener = HenCoderScaleGestureListener()
    private val scaleGestureDetector = ScaleGestureDetector(context, scaleGestureListener)

    /**
     * 快速滑动操作处理
     */
    private val flingRunnable = HenCoderFlingRunnable()

    /**
     * 当前缩放比
     */
    private var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale)

    private val scroller = OverScroller(context)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        originalOffsetX = (width - IMAGE_SIZE) / 2
        originalOffsetY = (height - IMAGE_SIZE) / 2

        //根据图片形状，计算小缩放比和大缩放比
        //小缩放比：指长的那一边，占满屏幕
        //大缩放比：指短的那一边，占满屏幕
        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            smallScale = width / bitmap.width.toFloat()
            bigScale = height / bitmap.height.toFloat() * EXTRA_SCALE_FACTOR
        } else {
            smallScale = height / bitmap.height.toFloat()
            bigScale = width / bitmap.width.toFloat() * EXTRA_SCALE_FACTOR
        }
        //更新动画内部值
        scaleAnimator.setFloatValues(smallScale, bigScale)
        currentScale = smallScale
    }

    override fun onDraw(canvas: Canvas) {
        //计算动画完成百分比
        val scaleFraction = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        //如果已经被双指缩放接管，则不再处理双击放大
        if (!scaleGestureDetector.isInProgress) {
            gestureDetector.onTouchEvent(event)
        }
        return true
    }

    private fun getAvatar(): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.mipmap.ic_avatar, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = IMAGE_SIZE.toInt()
        return BitmapFactory.decodeResource(resources, R.mipmap.ic_avatar, options)
    }


    inner class HenCoderGestureListener : GestureDetector.SimpleOnGestureListener() {
        /**
         * 按下时触发，也就是说是否需要这个事件流，如果需要，返回true拦截即可
         * 一般都会返回true的
         * @param e The down motion event.
         */
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        /**
         * 按下100ms时触发
         * @param e The down motion event
         */
        override fun onShowPress(e: MotionEvent?) {
        }

        /**
         * 点击抬起时触发
         *
         * @param e The up motion event that completed the first tap
         * @return true if the event is consumed, else false
         */
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return false
        }

        /**
         * 手指放在屏幕上，滑动时触发
         *
         * @param e1 The first down motion event that started the scrolling.
         * @param e2 The move motion event that triggered the current onScroll.
         * @param distanceX The distance along the X axis that has been scrolled since the last
         * call to onScroll. This is NOT the distance between `e1`
         * and `e2`.
         * @param distanceY The distance along the Y axis that has been scrolled since the last
         * call to onScroll. This is NOT the distance between `e1`
         * and `e2`.
         * @return true if the event is consumed, else false
         */
        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if (isUseBig) {
                offsetX -= distanceX
                offsetY -= distanceY

                fixOffsets()
                invalidate()
            }
            return false
        }

        private fun fixOffsets() {
            //限制x轴移动范围：移动范围 = (大图宽度-屏幕宽度)/2
            //同理限制y轴移动范围
            offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
            offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)
            offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
            offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
        }

        /**
         * 长按
         * @param e The initial on down motion event that started the longpress.
         */
        override fun onLongPress(e: MotionEvent?) {
        }

        /**
         * 手指在屏幕上，快速滑动并抬起
         *
         * @param e1 The first down motion event that started the fling.
         * @param e2 The move motion event that triggered the current onFling.
         * @param velocityX x轴速率，也就是单位时间内产生的位移距离
         * @param velocityY y轴速率
         * @return true if the event is consumed, else false
         */
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (isUseBig) {
                scroller.fling(
                    offsetX.toInt(),
                    offsetY.toInt(),
                    velocityX.toInt(),
                    velocityY.toInt(),
                    (-(bitmap.width * bigScale - width) / 2).toInt(),
                    ((bitmap.width * bigScale - width) / 2).toInt(),
                    (-(bitmap.height * bigScale - height) / 2).toInt(),
                    ((bitmap.height * bigScale - height) / 2).toInt(),
                    40.dp.toInt(),
                    40.dp.toInt()
                )
                ViewCompat.postOnAnimation(this@ScalableImageView, flingRunnable)
            }
            return false
        }


        /**
         *Notified when a single-tap occurs.
         * Unlike GestureDetector.OnGestureListener.onSingleTapUp(MotionEvent),
         * this will only be called after the detector is confident that the user's first tap is not followed by a second tap leading to a double-tap gesture.
         *
         * @param e The down motion event of the single-tap.
         * @return true if the event is consumed, else false
         */
        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            return false
        }

        /**
         * 双击抬起
         * 按下抬起，300ms之内再进行一次按下抬起，视为双击
         * 40ms之内按下抬起2次，视为手抖
         *
         * @param e The down motion event of the first tap of the double-tap.
         * @return true if the event is consumed, else false
         */
        override fun onDoubleTap(e: MotionEvent): Boolean {
            isUseBig = !isUseBig
            if (isUseBig) {
                //实现双击哪里，放大哪里的跟手效果
                offsetX = (e.x - width / 2f) * (1 - bigScale / smallScale)
                offsetY = (e.y - height / 2f) * (1 - bigScale / smallScale)
                fixOffsets()
                scaleAnimator.start()
            } else {
                scaleAnimator.reverse()
            }
            return true
        }

        /**
         * 双击之后的后续操作
         *
         * @param e The motion event that occurred during the double-tap gesture.
         * @return true if the event is consumed, else false
         */
        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            return false
        }

    }


    inner class HenCoderFlingRunnable : Runnable {

        override fun run() {
            //计算当前的位移信息
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.currX.toFloat()
                offsetY = scroller.currY.toFloat()
                invalidate()

                ViewCompat.postOnAnimation(this@ScalableImageView, this)
            }
        }

    }

    inner class HenCoderScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {
        /**
         * Responds to scaling events for a gesture in progress.
         * Reported by pointer motion.
         *
         * @param detector The detector reporting the event - use this to
         * retrieve extended info about event state.
         * @return true返回和上一刻的比值、false返回和初始值的比值
         */
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            var tempCurrentScale = currentScale * detector.scaleFactor
            if (tempCurrentScale < smallScale || tempCurrentScale > bigScale) {
                return false
            } else {
                currentScale *= detector.scaleFactor
                return true
            }
        }

        /**
         * Responds to the beginning of a scaling gesture. Reported by
         * new pointers going down.
         *
         * @param detector The detector reporting the event - use this to
         * retrieve extended info about event state.
         * @return Whether or not the detector should continue recognizing
         * this gesture. For example, if a gesture is beginning
         * with a focal point outside of a region where it makes
         * sense, onScaleBegin() may return false to ignore the
         * rest of the gesture.
         */
        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            //实现双指缩放哪里，哪里进行缩放的跟手效果
            offsetX = (detector.focusX - width / 2f) * (1 - bigScale / smallScale)
            offsetY = (detector.focusY - height / 2f) * (1 - bigScale / smallScale)
            return true
        }

        /**
         * Responds to the end of a scale gesture. Reported by existing
         * pointers going up.
         *
         * Once a scale has ended, [ScaleGestureDetector.getFocusX]
         * and [ScaleGestureDetector.getFocusY] will return focal point
         * of the pointers remaining on the screen.
         *
         * @param detector The detector reporting the event - use this to
         * retrieve extended info about event state.
         */
        override fun onScaleEnd(detector: ScaleGestureDetector?) {

        }

    }
}