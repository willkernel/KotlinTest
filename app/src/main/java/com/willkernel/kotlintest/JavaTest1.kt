package com.willkernel.kotlintest

/**
 * Created by willkernel
 * on 2019/3/22.
 */
fun main() {
    var a: Byte = 2
    var b: Short = 2
    var c: Int = 3
    var d: Long = 4L//长整型由大写字母L标记
    var e: Float = 5f //单精度浮点型由小写字母f或大写字符F标记
    var f: Double = 6.0
    println("$a $b $c $d $e $f")

    var g = 0x0F  //十六进制数
    var h = 0b00111011  //二进制数 32+16+8+1+2
    var k = 123   //十进制数
    println("$g $h $k")

//    数字类型字面常量的下划线
//    作用：分割数字进行分组，使数字常量更易读
    var oneMillion = 1_000_000
    var creditCard = 1234_5678_1234_3446L
    var socialNumber = 999_99_9999L
    var hexBytes = 0xFF_EE_FF_EC //4293853164  3482568641
    var bytes = 0b11001111_10010011_11000111_11000001
    print(oneMillion, creditCard, socialNumber, hexBytes, bytes)

    val number = 128 //123
    val numberBox: Int? = number
    print("boxed $numberBox")
    print(number == numberBox)
    /*
  上面定义的变量是Int类型，大于127其内存地址不同，反之则相同。
  这是`kotlin`的缓存策略导致的，而缓存的范围是` -128 ~ 127 `。
  故，下面的打印为false
*/
    print(number === numberBox)

//    转换
//    显式转换
    var num = 97
    print(
        num.toByte(),
        num.toUByte(),
        num.toInt(),
        num.toChar(),
        num.toDouble(),
        num.toFloat(),
        num.toLong(),
        num.toShort(),
        num.toString()
    )
//    隐式转换
    val num1 = 30L + 12
    print(num1)

//    位运算符
//    Kotlin中对于按位操作，和Java是有很大的差别的。Kotlin中没有特殊的字符，但是只能命名为可以以中缀形式调用的函数，
//    下列是按位操作的完整列表(仅适用于整形（Int）和长整形（Long）)
//   支持序列如下：shl、shr、ushr、and、or、xor
    var operaNum: Int = 4  //100
    var shlNum = operaNum shl 2
    var shrNum = operaNum shr 2
    var ushrNum = operaNum ushr 2
    var andNum = operaNum and 2
    var orNum = operaNum or 2
    var xorNum = operaNum xor 5
    var invNum = operaNum.inv()
    print(shlNum, shrNum, ushrNum, andNum, orNum, xorNum, invNum)

    var n: Int = -4
    println("无符号右移 ${n ushr 1}")
    //boolean
    var isNum: Boolean
    isNum = false
    print("isNum $isNum")

    // Char为表示字符型，字符变量用单引号（‘ ’）表示。并且不能直接视为数字，不过可以显式转换为数字
    var ch: Char
    ch = 'a'
    print(ch, ch.toByte(), ch.toInt(), ch.toShort(), ch.toString(), ch.toFloat())

//    除了可以转换类型外，当变量为英文字母时还支持大小写转换
    var charA = 'a'
    var charB = 'B'
    var charNum = '1'
    var result: Char
// 转换为大写
    result = charA.toUpperCase()
    print(result)
// 转换为小写
    result = charB.toLowerCase()
    print(result)
//当字符变量不为英文字母时，转换无效
    result = charNum.toUpperCase()
    print(result)

    //字符转义
    print("\n 换行符", "\t 制表符", "\b 退格", "\r enter", "\\", "\'", "\$", "\uff01")

    //字符串类型
    var str: String = "kotlin"
    print("> $str")
    for (c in str) {
        print(c)
    }

    var s1 = "hello\tkotlin"
    var s2 = "hello kotlin"
    print(s1, s2)

    var s3 = """fun main(){
           println()
        }"""
    print(s3)
//    可以使用trimMargin()函数删除前导空格 ，默认使用符号(|)作为距前缀，当然也可以使用其他字符。例：右尖括号（>）、左尖括号（<）等
    var s4 = """fun main(){
           |println()
        |}""".trimMargin("|")
    print(s4)

    var s5 = """fun main(){
           >println()
       >}""".trimMargin(">")
    print(s5)

    var s6 = "I'm coming"
    var s7 = "${s5.length}"
    var s8 = "$s6 hhh"
    print(s7, s8)


/*    数组型（Array） Kotlin中数组由Array<T>表示，可以去看看源码实现，里面就几个方法
    创建数组的3个函数
    arrayOf()
    arrayOfNulls()
    工厂函数（Array()）*/
    var ary = arrayOf(1, 3, 4, "test")
    for (i in ary) {
        print(i)
    }

    var ary2 = arrayOfNulls<Int>(3)
    for (item in ary2) {
        print(item)
    }

    ary2[0] = 1
    ary2[1] = 2
    ary2[2] = 3
    for (i in ary2) {
        print(i)
    }
    print("\n")

}

fun print(vararg array: Any) {
    array.forEach { println(it.toString()) }
}