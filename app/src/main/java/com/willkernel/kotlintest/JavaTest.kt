package com.willkernel.kotlintest

import com.willkernel.kotlintest.TestClass.Companion.NUM_C
import com.willkernel.kotlintest.TestConst.NUM_B

/**
 * Created by willkernel
 * on 2019/3/21.
 */
//fun main(args: Array<String>) {
fun main() {
    println("kotlin coming")

//    立即初始化
    var a: Int = 10

//    推导出类型
    var b = 5

//    没有初始化的时候，必须声明类型
    var c: Float
    c = 12.3f
    c += 1

    println("$a $b $c")

    Test1()
    Test2()

}

//类中声明变量 必须初始化，如果不初始化，需使用lateinit关键字 后期初始化
//后期初始化属性
//
//声明后期初始化属性的特点：
//
//使用lateinit关键字
//必须是可读且可写的变量，即用var声明的变量
//不能声明于可空变量。
//不能声明于基本数据类型变量。例：Int、Float、Double等，注意：String类型是可以的。
//声明后，在使用该变量前必须赋值，不然会抛出UninitializedPropertyAccessException异常
class Test1 {
    var a: Int = 0
    val b: Int = 1
    var c: String = ""
    lateinit var d: String
//    lateinit 不能使用在基本类型
//    lateinit var d:Int

    init {
        a = 2
        println("$a $b")
    }
}

//延迟初始化属性
//
//所谓延迟初始化即：指当程序在第一次使用到这个变量（属性）的时候在初始化。
//
//声明延迟初始化属性的特点：
//
//使用lazy{}高阶函数，不能用于类型推断。且该函数在变量的数据类型后面，用by链接。
//必须是只读变量，即用val声明的变量
class Test2 {
    private val mT: Array<String> by lazy {
        arrayOf(
            "first",
            "sec",
            "final"
        )
    }

    private val mStr: String by lazy { "lazy str" }
    var a: Int? = 0
    val b: Int? = null

    init {
        a = 0
        println("$a $b")

        println("$NUM_A $NUM_B $NUM_C")
    }
}

//其特点：const只能修饰val，不能修饰var
//
//声明常量的三种正确方式
//
//在顶层声明
//在object修饰的类中声明，在kotlin中称为对象声明，它相当于Java中一种形式的单例类
//在伴生对象中声明
// 1. 顶层声明
const val NUM_A : String = "顶层声明"

// 2. 在object修饰的类中
object TestConst{
    const val NUM_B = "object修饰的类中"
}

// 3. 伴生对象中
class TestClass{
    companion object {
        const val NUM_C = "伴生对象中声明"
    }
}