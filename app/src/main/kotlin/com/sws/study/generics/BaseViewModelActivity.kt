package com.sws.study.generics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.reflect.TypeToken
import java.io.Serializable

/**
 * @author mengyuan
 * @date 2022/9/1/6:54 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 */
open class BaseViewModelActivity<VM : ViewModel> : ComponentActivity() {


    val viewModel by lazy {
        ViewModelProvider(this).get(TestViewModel::class.java)
//        ViewModelProvider(this).get(getViewModel())
    }


    private fun getViewModel(): Class<VM> {
        val bean: TypeToken<VM> = object : TypeToken<VM>() {}
        return bean.rawType as Class<VM>
    }


    interface Test<T : Serializable> {
        fun test(serializable: T)

        fun <Q> test2(param: Q, param2: List<Q>): Q
    }

}