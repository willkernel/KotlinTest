package com.willkernel.kotlintest

/**
 * Created by willkernel
 * on 2019/3/23.
 */


fun main() {
    // 成员函数的使用：先初始化对象，在根据对象使用`中缀符号(.)`调用其成员函数
    FunctionTest().basis()
    var x = FunctionTest().basis()

    basis()
    val y = basis()

    println("x $x y $y") //Unit类型：该类型即无返回值的情况，可以省略。

    returnFun()

    funArgs(1, 2f)

    defArgs(numB = 3f, numC = true)

    println(F().foo())
    println(F().foo(2))

    callFun("str")
    callFun("str", true, 2)
    callFun("str", isTrue = true, numB = 2f)//使用命名参数我们可以使代码更具有可读性

    varargFun(1, "a", "b", "c")

//    在传递参数值时，我们可以一个一个参数的传递，或者可以直接传递一个当前定义类型的数组。不过在传递数组时，请使用伸展操作符( * )
    val ary = arrayOf("aaa", "bbb", "ccc")
    varargFun(2, *ary)

    println(test1())
    println(test2(2))
    println(test3(1f, 2))
}

fun basis() {
    return
}

private fun returnFun(): Int {
    return 2
}

fun funArgs(numA: Int, numB: Float) {
    print("numA=$numA numB=$numB")
}

fun defArgs(numA: Int = 1, numB: Float = 2f, numC: Boolean = false) {
    print("numA=$numA numB=$numB numC=$numC")
}

fun callFun(str: String, isTrue: Boolean = false, numA: Int = 2, numB: Float = 2f, numC: Int = 2) {}

fun varargFun(numA: Int, vararg str: String) {
//    str.forEach { it.plus(numA) }
//    str.map { print(it) }
    str.filter { it == "a" }.map { print(it) }
}

// 单表达式
fun test1() = 2  //无参数，自动推断类型为Int

fun test2(num: Int) = num * 2
fun test3(x: Float, y: Int = 2) = x * y

class F : FunctionTest() {
    override fun foo(i: Int): Int {
        return i * 2
    }
}

open class FunctionTest {
    //继承使用open修饰,默认是final,类，方法都一样
    //    声明一个类，在类中在定义一个方法，这个方法就是这个类的成员函数
    fun basis() {

    }

    open fun foo(i: Int = 10): Int {
        return 1
    }
}
