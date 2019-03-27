package com.willkernel.kotlintest

import java.util.regex.Pattern

/**
 * Created by willkernel
 * on 2019/3/23.
 */
fun main() {
    var str = "kotlin"
    println("first ${str.first()}  ${str[0]}  ${str.get(0)}")

    //查找到元素后返回对应的字母
    println(str.first { it == 'o' }) //查找不到满足条件的元素NoSuchElementException: Char sequence contains no character matching the predicate.

    str = ""
    //上面的函数会抛出异常，下面的函数返回null
    println(str.firstOrNull())
    println(str.firstOrNull { it == 'o' })

    str = "helloworld"
    println("last ${str.last()}  ${str[str.lastIndex]}  ${str.get(str.lastIndex)}")
    println(str.last { it == 'o' })
    println(str.lastOrNull())
    println(str.lastOrNull { it == 'o' })
    println("======")
    println(str.find { it == 'o' })
    println(str.findLast { it == 'o' })
    println("======")
    println(str.indexOfFirst { it == 'o' })
    println(str.indexOfLast { it == 'o' })
    println(str.indexOf('o', 1))
    println(str.indexOf("world", 1))
    println(str.lastIndexOf('o'))
    println(str.lastIndexOf("world"))
    println("======")
    str = "Kotlin is a very good programming language"
    println("s = ${str.substring(10)}") // 当只有开始下标时，结束下标为length - 1
    println(str.substring(0, 10))
    println(str.substring(IntRange(0, 12)))
    println("======")
    println(str.subSequence(0, 15))
    println(str.subSequence(IntRange(0, 15)))
    println("replace======")
    println(str.replace("kotlin", "Flutter", false))
    println(str.replace("Kotlin", "Flutter", true))

    str = "123 kotlin is good"
    println(str.replace(Regex("[0-9]+"), "kotlin"))

    str = "1234a kotlin 5678 kotlin 3333"
    println(str.replace(Regex("[0-9]+"), "abcd"))

    println(str.replaceFirst("123", ""))
    println(str.replaceBefore('a', "AA"))
    println(str.replaceBefore("kotlin", "Java"))
    println(str.replaceAfter("kotlin", "Java"))
    println(str.replaceAfterLast("kotlin", "Java"))
    println("replace======")
    var str2 = "1 kotlin 2 java 3 Lua 4 JavaScript"

    val list3 = str2.split(Regex("[0-9]+"))
    for (s in list3) {
        print("$s \t")
    }

    println()
    val list4 = str2.split(Pattern.compile("[0-9]+"))
    for (str in list4) {
        print("$str \t")
    }

//    这里举例一个可变参数情况的例子：
    val str3 = "a b c d e f g h 2+3+4+5"
    val list5 = str3.split(' ', '+')
    for (s in list5) {
        print("$s \t")
    }

    val str4 = "kotlin very good"

// 1. 直接用length属性获取
    println("str4.length => ${str4.length}")

// 2. 用count()函数获取
    println("str4.count() => ${str4.count()}")
//    统计字符串中重复某个字符的个数
    println("str4.count() => ${str4.count { it == 'o' }}")

    val str5 : String? = "kotlin"

// 可以看出当str为可空变量的时候，isNullOrEmpty()和isNotOrBlank()可以不做直接调用而不做任何处理，而其他的函数则不行
    println(str5?.isEmpty())
    println(str5?.isNotEmpty())
    println(str5.isNullOrEmpty())
    println(str5?.isBlank())
    println(str5?.isNotBlank())
    println(str5.isNullOrBlank())

    val str6 = "kotlin"
    println("字符串反转：${str6.reversed()}")

    val str7 = "kotlin"
    str7.startsWith('k')         // 是否有字符`k`起始
    str7.startsWith("Kot")       // 是否由字符串`kot`起始
    str7.startsWith("lin",3)     // 当起始位置为3时，是否由字符串`lin`起始

    val str8 = "kotlin"
    println(str8.endsWith("lin"))  // 是否由字符串`lin`结尾
    println(str8.endsWith('n'))    // 是否由字符`n`结尾
}