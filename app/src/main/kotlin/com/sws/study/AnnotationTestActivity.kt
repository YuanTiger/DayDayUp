package com.sws.study

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.sws.annotations.MyBindView
import com.sws.reflection.MyBinding

/**
 * @author mengyuan
 * @date 2022/8/25/4:57 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 自定义注解测试
 */
class AnnotationTestActivity : ComponentActivity() {

    @JvmField
    @MyBindView(R.id.tv_text)
    var tvText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotation_test)
        //        tvText = findViewById(R.id.tv_text);
//        binding(this)
        MyBinding.binding(this)
        tvText!!.text = "use code set content"
    }
}


fun binding(activity: AnnotationTestActivity) {
    //拿到所有变量
    val declaredFields = activity.javaClass.declaredFields
    for (field in declaredFields) {
        //找到所有带有MyBindView注解的变量
        val bindView = field.getAnnotation(MyBindView::class.java)
        if (bindView != null) {
            try {
                field.isAccessible = true
                field[activity] = activity.findViewById(bindView.value)
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }
}
