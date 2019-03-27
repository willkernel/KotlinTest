package com.willkernel.kotlintest

import java.util.*

/**
控制语句
 */
fun main() {
    var num1 = 2
    if (num1 == 2) {
        println("true")
    } else {
        println("false")
    }

// 在Java中可以这么写，但是Kotlin中直接会报错。
//    var num2 =num1>2?3:

//    Kotlin中的三元运算符
//    在Kotlin中其实是不存在三元运算符(condition ? then : else)这种操作的。
//    那是因为if语句的特性(if表达式会返回一个值)故而不需要三元运算符
    var num2 = if (num1 > 2) 3 else 5
    print(num2)

    var numA: Int = 2
    var numC: Int = if (numA > 2) {
        numA++
        numA = 10
        println("numA > 2 => true")
        numA
    } else if (numA == 2) {
        numA++
        println("numA == 2 => true")
        numA = 20
        numA
    } else {
        numA++
        numA = 30
        println("numA < 2 => true")
        numA
    }

// 根据上面的代码可以看出，每一个if分支里面都是一个代码块，并且返回了一个值。根据条件numC的值应该为20
    println("numC => $numC")

    // 循环5次，且步长为1的递增
    for (i in 0 until 5) {
        print("i => $i \t")
    }

    for (i in 15 downTo 10) {
        print("i => $i\t")
    }

//    符号（' .. '） 表示递增的循环的另外一种操作
//使用符号( '..').
//范围：..[n,m]=> 即大于等于n，小于等于m
//和until的区别，一是简便性。二是范围的不同
    for (i in 20..25) {
        print("i=>$i\t")
    }

    for (i in 20 until 25) {
        print("i=>$i\t")
    }
//    设置步长
    for (i in 10..16 step 2) {
        print("i=>$i\t")
    }

    for (i in "abcdef") {
        print("i=>$i\t")

    }

    var ary = arrayOf(1, 3, 5, 7, 9)
    for (i in ary.indices) {
        println("i>${ary[i]}")
    }

    for ((index, value) in ary.withIndex()) {
        print("$index $value")
    }


    var ary3 = Array(5) { index -> (index * 2).toString() }
    println(Arrays.toString(ary3))

//    使用列表或数组的扩展函数遍历
    var arrayList = arrayOf(2, 'a', 3, false, 8)
    var iterator = arrayList.iterator()
    while (iterator.hasNext()) {
        println(iterator.next())
    }

    when (5) {
        1 -> {
            println("1")
        }
        2 -> println("2")
        3 -> println("3")
        5 -> {
            println("5")
        }
        else -> {
            println("0")
        }
    }

    when (1) {
        1, 2, 3 -> {
            print("1")
        }
        5 -> {
            print("5")
        }
        in 6..9 -> print("test in")
        else -> {
            print("else")
        }
    }

    when (num1 > 5) {
        true -> print(">5")
        false -> print("<5")
    }

    var arr = arrayOf(1, 5, 8)
    when (1) {
        in arr.toIntArray() -> print("1 exist in")
        in 0..10 -> print("in 0~10")
        !in 5..10 -> print("!in 5~10")
        else -> {
            print("both err")
        }
    }

    when ("abc") {
        is String -> print("is string")
        else -> print("not string")
    }

    var a = 2
    when (a) {
        !is Int -> print("not int")
        else -> {
            a = a.shl(2)
            print(a)
        }
    }

    when {
        true -> {
            for (i in arr) {
                print(i)
            }
        }
        false -> {

        }
    }
    var num = 5
    var count = 1
    while (num < 10) {
        println("num => $num")
        println("循环了$count 次")
        count++
        num++
    }
    num = 5
    count = 1
    do {
        print("num>$num")
        print("count>$count")
        num++
        count++
    } while (num < 10)

    num = 5
    count = 1
    do {
        println("num => $num")
        println("循环了$count 次")
        count++
        num++
    } while (num < 5)

    returnExample()

    count = 0
    for (i in 1 until 10) {
        if (i == 5) {
            println("我在第$i 次退出了循环")
            break
        }
        count++
    }
    println("我循环了多少次：count => $count")

    for (i in 1 until 10) {
        if (i == 5) {
            println("我跳过了第$i 次循环")
            continue
        }
        println("i => $i")
    }
}

private fun returnExample() {
    var str: String = ""
    if (str.isBlank()) {
        println("我退出了该方法")
        return
    }
}
