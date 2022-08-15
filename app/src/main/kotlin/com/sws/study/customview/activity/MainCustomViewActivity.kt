package com.sws.study.customview.activity

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.sws.study.R
import com.sws.study.utils.router

/**
 * @author mengyuan
 * @date 2022/7/14/4:48 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 自定义View入口
 * 包含了自定义View的相关学习内容
 */
class MainCustomViewActivity : ComponentActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_custom_view)

        findViewById<View>(R.id.bt_clip).setOnClickListener(this)
        findViewById<View>(R.id.bt_sector).setOnClickListener(this)
        findViewById<View>(R.id.bt_xfermode).setOnClickListener(this)
        findViewById<View>(R.id.bt_text_view_measure).setOnClickListener(this)
        findViewById<View>(R.id.bt_camera_anim).setOnClickListener(this)
        findViewById<View>(R.id.bt_bitmap_drawable).setOnClickListener(this)
        findViewById<View>(R.id.bt_material_edittext).setOnClickListener(this)
        findViewById<View>(R.id.bt_measure).setOnClickListener(this)
        findViewById<View>(R.id.bt_tag_view).setOnClickListener(this)
        findViewById<View>(R.id.bt_touch).setOnClickListener(this)
        findViewById<View>(R.id.bt_scalable_view).setOnClickListener(this)
        findViewById<View>(R.id.bt_multi_touch).setOnClickListener(this)
        findViewById<View>(R.id.bt_view_pager).setOnClickListener(this)
        findViewById<View>(R.id.bt_drag).setOnClickListener(this)
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bt_clip -> {
                router(ClipActivity::class.java)
            }
            R.id.bt_sector -> {
                router(SectorActivity::class.java)
            }
            R.id.bt_xfermode -> {
                router(XfermodeActivity::class.java)
            }
            R.id.bt_text_view_measure -> {
                router(CustomViewTextViewActivity::class.java)
            }
            R.id.bt_camera_anim -> {
                router(CameraActivity::class.java)
            }
            R.id.bt_bitmap_drawable -> {
                router(BitmapDrawableActivity::class.java)
            }
            R.id.bt_material_edittext -> {
                router(MaterialEditTextActivity::class.java)
            }
            R.id.bt_measure -> {
                router(MeasureActivity::class.java)
            }
            R.id.bt_tag_view -> {
                router(TagViewActivity::class.java)
            }
            R.id.bt_touch -> {
                router(TouchActivity::class.java)
            }
            R.id.bt_scalable_view -> {
                router(ScalableImageViewActivity::class.java)
            }
            R.id.bt_multi_touch -> {
                router(MultiTouchActivity::class.java)
            }
            R.id.bt_view_pager -> {
                router(TwoViewPagerActivity::class.java)
            }
            R.id.bt_drag -> {
                router(DragActivity::class.java)
            }
        }
    }
}