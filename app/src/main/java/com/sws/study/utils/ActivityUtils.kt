package com.sws.study.utils

import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity

/**
 * @author mengyuan
 * @date 2022/7/15/1:44 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 */
fun ComponentActivity.router(className: Class<*>) {
    val intent = Intent(this, className)
    startActivity(intent)
}


fun ComponentActivity.toastShow(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}