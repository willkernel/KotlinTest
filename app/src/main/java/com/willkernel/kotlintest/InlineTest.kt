package com.willkernel.kotlintest

/**
使用高阶函数会带来一些运行时的效率损失：每一个函数都是一个对象，并且会捕获一个闭包。 即那些在函数体内会访问到的变量。
内存分配（对于函数对象和类）和虚拟调用会引入运行时间开销
但是在许多情况下通过内联化 lambda 表达式可以消除这类的开销
 */
fun main() {
    doCal(1, 2) { a, b ->
        a + b
    }
//    return 只可以用在有名字的函数，或者匿名函数中，使得该函数执行完毕。
//    而针对lambda表达式，你不能直接使用return

//    可以使用return+label的形式，将这个lambda结束
    go die@{ if (it == 1) return@die else println(it) }
//    go { if (it == 1) return@go else println(it) }
    //1 return print : 2,3

//    你的lambda应用在一个内联函数的时候，这时候你可以在lambda中使用return
//    可以这么理解，内联函数在编译的时候，将相关的代码贴入你调用的地方。
//    lambda表达式就是一段代码而已，这时候你在lambda中的return，相当于在你调用的方法内return
//    sayHello {
//        return
//    }

    findParentOfType(Int::class.java)
    findParentOfType<Int>()

//    实例
    val arr = listOf(1, 2, 3)
    arr.forEach { if (it == 2) return }


    println("end main")//不会执行
}

inline fun sayHello(a: () -> Unit) {
    print("hello")
    a()
    print("end")//没有调用
}

//当你声明doCal为内联函数的时候，这个函数所使用的lambda（也就是函数）也默认为内联函数
//也可以改变这种行为
//inline fun doCal(a: Int, b: Int, noinline cal: (a: Int, b: Int) -> Int) {
inline fun doCal(a: Int, b: Int, cal: (a: Int, b: Int) -> Int) {
    cal(a, b)
}

fun go(f: (i: Int) -> Unit) {
    var i = 0
    while (i++ < 3) {
        f(i)
    }
}

fun <T> findParentOfType(clazz: Class<T>): T? {
    var p = parent
    while (p != null && !clazz.isInstance(p)) {
        p = p.parent
    }
    @Suppress("UNCHECKED_CAST")
    return p as T?
}

//内联函数支持 具体的类型参数的声明 reified
inline fun <reified T> findParentOfType(): T? {
    var p = parent
    while (p != null && p !is T) {
        p = p.parent
    }
    return p as T?
}