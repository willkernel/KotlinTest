package com.willkernel.kotlintest

/**
声明一个数据类时，编辑器自动为这个类做了一些事情，比Java简洁
它会根据主构造函数中所定义的所有属性自动生成下列方法：
生成equals()函数与hasCode()函数
生成toString()函数，由类名（参数1 = 值1，参数2 = 值2，....）构成
由所定义的属性自动生成component1()、component2()、...、componentN()函数，其对应于属性的声明顺序。
copy()函数

当这些函数中的任何一个在类体中显式定义或继承自其基类型，则不会生成该函数

主构造函数需要至少有一个参数
主构造函数的所有参数需要标记为 val 或 var；
数据类不能是抽象、开放、密封或者内部的；
数据类是可以实现接口的，如(序列化接口)，同时也是可以继承其他类的，如继承自一个密封类。
 */
data class People(var name: String, var age: Int, var sex: Int)

data class User(val name: String, val pwd: String)

fun main() {
    var p = People("wk", 1, 0)
    println(p)

    val mUser = User("kotlin", "123456")
    print(mUser)

//    Koltin要修改数据类的属性，则使用其独有的copy()函数。其作用就是：修改部分属性，但是保持其他不变
    val mNewUser = mUser.copy(name = "new kotlin")
    print(mNewUser)

//    解构声明
    val (name, pwd) = mUser
    print("name=$name pwd=$pwd")

//    系统标准库中的标准数据类
//    标准库提供了 Pair 和 Triple  源码位置kotlin\util\Tuples.kt
    val pair = Pair(1, 2)
    val triple = Triple(1, 2, 3)

    println("$pair $triple")// 打印：即调用了各自的toString()方法
    println(pair.toList())// 转换成List集合
    println(triple.toList())// 转换成List集合
    println(pair to 3) // Pair类特有: 其作用是把参数Pair类中的第二个参数替换  ((1, 2), 3)
    println(pair.first to 3) // Pair类特有: 其作用是把参数Pair类中的第二个参数替换 (1, 3)

//    val s=SealedExpr() //无法访问<init>,编译错误
    val s1 = SealedExpr.P(1, 2)
    print(s1)

    val s2 = SealedExpr.P(3, 4)
    print(s2)
//    枚举类的中的每一个枚举常量都只能存在一个实例。而密封类的子类可以存在多个实例
    println(s1.hashCode())
    println(s2.hashCode())
    println(SealedExpr.Add.add(1, 2))

    eval(s2)
}

//密封类 定义密封类的关键字：sealed
//密封类是包含了一组受限的类集合，因为里面的类都是继承自这个密封类的。但是其和其他继承类（open）的区别在，密封类可以不被此文件外被继承，有效保护代码。
// 但是，其密封类的子类的扩展是是可以在程序中任何位置的，即可以不在同一文件下
//密封类是不能实例化，那么我们要怎么使用，或者说它的作用是什么呢
sealed class SealedExpr() {
    data class P(val num1: Int, val num2: Int) : SealedExpr()

    object Add : SealedExpr()// 单例模式
    companion object Minus : SealedExpr()// 单例模式
}

fun <T> SealedExpr.Add.add(num1: T, num2: T): Int {
    return 100
}
//上面的扩展功能没有任何的意义，只是为了给大家展示密封类子类的扩展不局限与密封类同文件这一个功能

// 其子类可以定在密封类外部，但是必须在同一文件中 v1.1之前只能定义在密封类内部
object NotANumber : SealedExpr()

fun eval(expr: SealedExpr): Any = when (expr) {
    is SealedExpr.Add -> println("is Add")
    is SealedExpr.Minus -> println("is Minus")
    is SealedExpr.P -> println(SealedExpr.P(11, 22))
    NotANumber -> Double.NaN
}
//使用密封类的好处
//有效的保护代码
//在使用when表达式 的时候，如果能够验证语句覆盖了所有情况，就不需要为该语句再添加一个else子句了