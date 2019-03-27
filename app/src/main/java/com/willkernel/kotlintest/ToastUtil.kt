package com.willkernel.kotlintest

import android.widget.Toast

/**
 * Created by willkernel
 * on 2019/3/21.
 * 静态变量和方法
 * 在 Kotlin 将这种方式称之为伴生对象
 */
object ToastUtil{
    var sToast:Toast?=null
    /**为空时，不抛出异常 safe(?.)*/
    fun show(){
        sToast?.setText("toast")
        sToast?.duration=Toast.LENGTH_LONG
        sToast?.show()
    }
    /**为空时，抛出异常 non-null asserted (!!.) 断言*/
    fun showToast(){
        sToast!!.show()
    }
}