package com.willkernel.kotlintest

/**
 * Created by willkernel
 * on 2019/3/22.
 */
fun main() {
    simpleUnaryOperator()

    complexUnaryOperator()

    simpleBinaryOperator()

    complexBinaryOperator()

    binaryOperator()

    rangeOperator()
}

fun rangeOperator() {
    val a = 1
    val b = 5

    val s=3 in a..b
    print("s $s")

    for (i in a..b) {
        print("i $i")
    }

    val t=3 in a.rangeTo(b)
    print("t $t")
    for (i in a.rangeTo(b)) {
        print("i $i")
    }
}

fun binaryOperator() {
    var a=16
    printAry(a shl 2,a shr 2,a ushr 2,a and 2,a or 2,a xor 2,a.inv())
}

fun complexBinaryOperator() {
    var a = 10
    var b = 2
    var c = "Kotlin"

// 主要演示字符串的+=
    c += a                          //等价于  c = c.plus(a)
    print("c = $c \t")

    a += b                          //等价于  a = a.plus(b)
    print("a = $a \t")

    a = 10
    a -= b                          //等价于  a = a.minus(b)
    print("a = $a \t")

    a = 10
    a *= b                          //等价于  a = a.tiems(b)
    print("a = $a \t")

    a = 10
    a /= b                          //等价于  a = a.div(b)
    print("a = $a \t")

    a = 10
    a % b                           //等价于  a = a.rem(b)
    print("a = $a \t")
}

fun simpleBinaryOperator() {
    // 简单的二元操作
    val a = 10
    val b = 2
    val c = "2"
    val d = "Kotlin"

    printAry("" + a + d, c + d, a + b, a - b, a * b, a / b, a % b)

//    printAry("${a+d}")//错误：字符串模板限制只能为数值型
    printAry(a.plus(b), a.minus(b), a.times(b), a.div(b), a.rem(b))
//    println(a.plus(d)) 错误：因为第一个操作数`a`限制了其plus()方法的参数，
    println(d.plus(a)) // 正确：因为plus()方法的参数为超（Any）类型
}

fun complexUnaryOperator() {
//操作符	重载	    表示
//a++	a.inc()	a = a.also{ a.inc() }
//a--	a.dec()	a = a.also{ a.dec() }
//++a	a.inc()	a = a.inc().also{ a = it }
//--a	a.dec()	a = a.dec().also{ a = it }
    var a = 10
    var b = 10
    var c = 10
    var d = 10
//    printAry("a++ = ${a++} \t b-- = ${b--} \t ++c = ${++c} \t --d = ${--d}")

    // 操作符重载方式实现，或许你看不明白上表中代码，不过这没关系，你只要记住上面前缀与后缀操作的区别就行
    a.also { a.inc() }
    b.also { b.dec() }
    c.inc().also { c = it }
    d.dec().also { d = it }
    printAry("a = $a \t b = $b \t c = $c \t d = $d")
}

private fun simpleUnaryOperator() {
    //    一元操作:即指一个操作数的情况，
//    操作符 重载
//    +a    a.unaryPlus ()
//    -a    a.unaryMinus ()
//    !a    a.not ()
    var a = 1
    var b = 2
    var c = true
    var d = false

//+ 表示为操作数实现一个正号的意思，其操作数为数值型
//- 表示为操作数实现一个负号的意思，其操作数为数值型
//! 表示取反的意思，其操作数为boolean类型
    printAry("${+a} ${-a} ${!c}")
    printAry("${+b} ${-b} ${!d}")
    println("====")
    printAry("${a.unaryPlus()} ${a.unaryMinus()} ${c.not()}")
    printAry("${b.unaryPlus()} ${b.unaryMinus()} ${d.not()}")
}

fun printAry(vararg array: Any) {
    array.forEach { print(it) }
}