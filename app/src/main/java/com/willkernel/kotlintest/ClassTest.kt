package com.willkernel.kotlintest

import org.intellij.lang.annotations.PrintFormat

/**
没有结构体的时候，大括号可以省略
 */
class ClassTest

/**在Kotlin中，允许有一个主构造函数和多个二级构造函数（辅助构造函数）
 * 主构造函数是类头的一部分，类名的后面跟上构造函数的关键字以及类型参数*/
class ClassTest1 constructor(num: Int)

/** 因为是默认的可见性修饰符且不存在任何的注释符
故而主构造函数constructor关键字可以省略*/
class ClassTest2(var num: Int) {//参数类型默认为val ，显示指明为var
init {
    num = 5
    print("num =$num")
}
}

/**声明属性的简便方法
即在主构造函数中声明*/
class ClassTest3(val num1: Int, val num2: Int, val num3: Long, var str: String) {

}

/**什么时候constructor可以省略
在构造函数不具有注释符或者默认的可见性修饰符时，constructor关键字可以省略。
默认的可见性修饰符时public。可以省略不写*/
class ClassTest4 private constructor(val num1: Int)

class ClassTest5 @PrintFormat constructor(val num1: Int)

/**辅助（二级）构造函数
Kotlin中支持二级构造函数。它们以constructor关键字作为前缀*/
class ClassTest6 {
    constructor(num: Int) {
        print(num)
    }
}

/**如果类具有主构造函数，则每个辅助构造函数需要通过另一个辅助构造函数直接或间接地委派给主构造函数。
 * 使用this关键字对同一类的另一个构造函数进行委派*/
class ClassTest7 constructor(num: Int) {
    init {
        print("num $num")
    }

    constructor(num: Int, str: String) : this(num) {
        print("num $num str $str")
    }

    constructor(str: String) : this(1) {
        print("str $str")
    }
}

/**当类的主构造函数都存在默认值时的情况

在JVM上，如果类主构造函数的所有参数都具有默认值，编译器将生成一个额外的无参数构造函数，它将使用默认值。 这使得更容易使用Kotlin与诸如Jackson或JPA的库，通过无参数构造函数创建类实例。
同理可看出，当类存在主构造函数并且有默认值时，二级构造函数也适用*/
open class ClassTest8 constructor(num1: Int = 10, num2: Int = 20) {
    init {
        print("clz 8 num1=$num1  num2=$num2")
    }

    constructor(num1: Int = 1, num2: Int = 2, num3: Int = 30) : this(num1, num2) {
        print("num1=$num1  num2=$num2  num3=$num3")
    }
}

/**继承，先执行父类的对应构造方法，再执行子类的构造函数*/
//class ClassTest9(num: Int = 1) : ClassTest8() {
class ClassTest9(num: Int = 1) : ClassTest8(num) {
    init {
        print("clz9 $num")
    }

    constructor(num1: Int, num2: Int) : this(num1 + num2) {
        print("num1=$num1  num2=$num2")
    }
}

fun main() {
    var test = ClassTest2(2)

    // 主构造函数先执行，再执行二级构造函数，this(num) 使用参数中的同类型参数
    var test1 = ClassTest7(1)
    var test2 = ClassTest7(2, "2")
    var test3 = ClassTest7("3")

    // 当实例化无参的构造函数时。使用了参数的默认值
    var test4 = ClassTest8()
    var test5 = ClassTest8(1, 2)
    var test6 = ClassTest8(3, 4, 5)
    print("====ClassTest9====")
    var test7 = ClassTest9()
    var test8 = ClassTest9(2)
}