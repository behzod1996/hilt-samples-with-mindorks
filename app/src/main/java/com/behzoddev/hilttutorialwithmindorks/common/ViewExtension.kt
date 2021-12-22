package com.behzoddev.hilttutorialwithmindorks.common

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.toastLong(msg: String) {
    Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
}
fun Context.toastShort(msg: String) {
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}
fun Fragment.toastLong(msg: String) {
    Toast.makeText(activity,msg,Toast.LENGTH_LONG).show()
}
fun Fragment.toastShort(msg: String) {
    Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show()
}