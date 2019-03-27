package com.willkernel.kotlintest

import android.app.Activity

/**
 * Created by willkernel
 * on 2019/3/21.
 * 抽象类
 */
abstract class BaseActivity:Activity() {
    abstract fun init()
    abstract fun handle():Int
}