package com.willkernel.kotlintest

/**
 * 关于一个网络请求结果的枚举类
 */
enum class EnumTest {
    NORMAL, NO_DATA, NO_INTERNET, ERROR, OTHER
}

enum class Color(var argb: Int) {
    RED(0xFF0000), WHITE(0xFFFFFF), BLACK(0x000000), GREEN(0x00FF00)
}

enum class ConsoleColor(var argb: Int) {
    RED(0xFF0000) {
        override fun print() {
            print("color RED")
        }
    },
    WHITE(0xFFFFFF) {
        override fun print() {
            print("color WHITE")
        }
    },
    BLACK(0x000000) {
        override fun print() {
            print("color BLACK")
        }
    },
    GREEN(0x00FF00) {
        override fun print() {
            print("color GREEN")
        }
    };

    abstract fun print()
}

fun main() {
    print(EnumTest.NORMAL.name)
    print(EnumTest.NO_DATA.name)
    print(EnumTest.NO_INTERNET.name)
    print(EnumTest.ERROR.name)
    print(EnumTest.OTHER.name)

    ConsoleColor.WHITE.print()
    ConsoleColor.RED.print()

    print(Color.RED.name + Color.RED.ordinal)
    print("name = ${Color.WHITE.name} ordinal = ${Color.WHITE.ordinal}")

    //使用 enumValues<T>()和 enumValueOf<T>()函数以泛型的方式访问枚举类中的常量
    println(enumValues<Color>().joinToString { it.name })
    println(enumValueOf<Color>("RED")) //IllegalArgumentException: No enum constant com.willkernel.kotlintest.Color.C

    println(Color.valueOf("RED"))
    println(Color.values()[1])//使用Color.values()[大于枚举常量位置]，则会抛出下标越界异常
    println(Color.values().joinToString { it.name })
}