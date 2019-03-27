package com.willkernel.kotlintest

/**
 * Created by willkernel
 * on 2019/3/21.
 * 泛型
 */
class Bean<T:Comparable<String>>(t:T) {
    var data=t
}
var bean=Bean("555")

