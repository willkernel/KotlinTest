package com.willkernel.kotlintest

/**
 * Created by willkernel
 * on 2019/3/25.
 */
interface InterfaceTest {
    fun f1()
}

interface Demo1Interface {

    /**
     * 定义一个无参数无返回值的方法
     */
    fun fun1()

    /**
     * 定义一个有参数的方法
     */
    fun fun2(num: Int)

    /**
     * 定义一个有参数有返回值的方法
     */
    fun fun3(num: Int): Int

    // 下面的两个方法是有结构体， 故可以不重写

    /**
     * 定义一个无参数有返回值的方法
     */
    fun fun4(): String {
        return "fun4"
    }

    /**
     * 定义一个无结构体函数，大括号是可以省略的
     * 如果函数中不存在表达式，大括号可以省略。
     * 如fun1一样
     */
    fun fun5() {
    }
}

class I1 : Demo1Interface {

    override fun fun1() {
        println("我是fun1()方法")
    }

    override fun fun2(num: Int) {
        println("我是fun2()方法，我的参数是$num")
    }

    override fun fun3(num: Int): Int {
        println("我是fun3()方法，我的参数是$num，并且返回一个Int类型的值")
        return num + 3
    }

    override fun fun4(): String {
        println("我是fun4()方法，并且返回一个String类型的值")

        /*
            接口中的fun4()方法默认返回”fun4“字符串.
            可以用super.fun4()返回默认值
            也可以不用super关键字，自己返回一个字符串
        */
        return super.fun4()
    }

    /*
         接口中的fun5()带有结构体，故而可以不用重写，
         fun4()同样
    */

    //    override fun fun5() {
    //        super.fun5()
    //    }
}

class I2 : InterfaceTest {
    override fun f1() {
        println("I1 f1()")
    }
}

interface Demo2Interface {
    val num1: Int
    val num2: Int
}

class I3(override val num1: Int, override val num2: Int) : Demo2Interface {
    fun sum(): Int {
        return num1 + num2
    }
}

interface Demo3Interface {
    val num3: Int
        get() = 3

    val num4: Int
}

class I4 : Demo3Interface {
    override val num3: Int
        get() = super.num3
    override val num4: Int
        get() = 4

    fun sum(): Int {
        return num3 + num4
    }
}

interface D4Interface {
    fun f1() {
        print("D4 f1()")
    }

    fun f2() {
        print("D4 f2()")
    }
}

interface D5Interface {
    fun f1() {
        print("D5 f1()")
    }

    fun f2() {
        print("D5 f1()")
    }
}

/**两个接口中都存在两个相同方法名的方法。因此编译器不知道应该选哪个，故而我们用super<接口名>.方法名来区分*/
class I5 : D4Interface, D5Interface {
    override fun f1() {
        super<D4Interface>.f1()
        super<D5Interface>.f1()
    }

    override fun f2() {
        super<D4Interface>.f2()
        super<D5Interface>.f2()
    }

}

fun main() {
    I2().f1()

    I1().fun1()
    I1().fun2(1)
    I1().fun3(2)

    print(I3(1, 2).sum())
    print(I4().sum())

    I5().f1()
    I5().f2()
}