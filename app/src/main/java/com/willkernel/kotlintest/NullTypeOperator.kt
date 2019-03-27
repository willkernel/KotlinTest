package com.willkernel.kotlintest

/**
 * Created by willkernel
 * on 2019/3/23.
 */
fun main() {
    var a: Int = 12
    val b: Int = 10
    val c = null
//    a=null 错误，不可空对象不能赋值为null
//    c=20
//    b=20 //val 不能被重新赋值

    if (a == null) {//false 变量a永远不可能null

    }

    var nullA: Int? = 12
    val nullB: Int? = 13

    nullA = null
    if (nullA == nullB) {
        print("nullA==nullB")
    }

    var str: String? = "12345"
    str = null
    println(str?.length)// 当变量str为null时，会返回空(null) 有效避免空引用异常（NullPointException），因为只要链式其中的一个为null，则整个表达式都为null

    var builder = BuilderTest.Builder().setName("name")?.setAge(10)?.setSex("man")
    println(builder.toString())

    builder = null
    builder?.setName("name")?.setAge(10)?.setSex("man")
    println(builder)

    println(nullMethod())

    methodLet()

    evilsMethod()
}

//Evils其实不是一个操作符，而是evil的复数，而evil的意思在这里可以理解为屏蔽、安全的操作符，这样的操作符有三种：
//
//?: 这个操作符表示在判断一个可空类型时，会返回一个我们自己设定好的默认值.
//!! 这个操作符表示在判断一个可空类型时，会显示的抛出空引用异常（NullPointException）.
//as? 这个操作符表示为安全的类型转换.

/**总结
这一章在实际的项目开发当中用到的地方是很多的，如果用好了各种空安全的操作符，估计你的项目中就不会抛出以及异常了，在这里我做了一个总结，希望会对各位有所帮助：

项目中会抛出空引用（NullPointerException）异常的情况

在可空类型变量的使用时，用了!!操作符
显式抛出空引用异常 throw NullPointerException()
外部 Java 代码导致的
对于初始化，有一些数据不一致(如一个未初始化的 this 用于构造函数的某个地方)
项目中会抛出类型转换（ClassCastException）异常的情况

在类型转换中使用了as操作符
使用了toXXX()方法不能转换的情况下
外部 Java 代码导致的
尽量避免使用的操作符

尽可能的不要使用!!操作符，多使用?:、?.操作符，以及let{}函数
尽可能的使用as?操作符去替换掉as,在不确定是否可以安全转换的情况下不使用toXXX()方法*/
fun evilsMethod() {
    val testStr: String? = null
    var length = 0
    // 例： 当testStr不为空时，输出其长度，反之输出-1
    // 传统写法
    length = if (testStr != null) testStr.length else -1
    // ?: 写法 此操作符一般和?.操作符连用。当且仅当?:左边的表达式为null时，才会执行?:右边的表达式。
    length = testStr?.length ?: -1
    println(length)

//    使用一个可空类型变量时，在该变量后面加上!!操作符，会显示的抛出NullPointException异常
//    println(testStr!!.length)

//    ClassCastException: java.lang.String cannot be cast to java.lang.Integer
//    val num:Int?="Kotlin" as Int
    val num: Int? = "Kotlin" as? Int
    print("num $num")
}

private fun methodLet() {
    val arr = arrayOf(1, 2, null, 4, null, 5, 6)
    for (i in arr) {
        if (i == null) {
            continue
        }
        println(i)
    }

//let操作符的作用：当使用符号?.验证的时候忽略掉null
//let的用法：变量?.let{ ... }
    for (i in arr) {
        i?.let { println("index> $it") }
    }
}

//如果方法中的代码使用?.去返回一个值，那么方法的返回值的类型后面也要加上?符号,反之亦然  Int? 和Int不是同一个类型
fun nullMethod(): Int? {
    val str: String? = "123"
    return str?.length
}

class BuilderTest {
    class Builder {
        private var name: String? = "wk"
        private var age: Int? = 0
        private var sex: String? = "man"

        fun setName(name: String): Builder? {
            this.name = name
            return this
        }

        fun setAge(age: Int): Builder? {
            this.age = age
            return this
        }

        fun setSex(sex: String): Builder? {
            this.sex = sex
            return this
        }

        override fun toString(): String {
            return "Builder(name=$name, age=$age, sex=$sex)"
        }

    }
}
