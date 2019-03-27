package com.willkernel.kotlintest

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
高阶函数源码位置  package {@link Standard.kt}
 */
class AdvancedTest : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mTv = TextView(this)
        mTv.run {
            text = "kotlin"
            textSize = 16f
            setTextColor(0xff0000)
        }
        setContentView(mTv)
//        with是正常的高阶函数，T.run()是扩展的高阶函数
//        with函数的返回值指定了receiver为接收者
        with(mTv) {
            text = "kotlin"
            textSize = 16f
            setTextColor(0xff0000)
        }

//        从T.apply()源码中在结合前面提到的T.run()函数的源码我们可以得出,这两个函数的逻辑差不多，
//        唯一的区别是T,apply执行完了block()函数后，返回了自身对象。而T.run是返回了执行的结果。
//        故而： T.apply的作用除了实现能实现T.run函数的作用外，还可以后续的再对此操作
        mTv.apply {
            text = "kotlin"
            textSize = 20f
        }.apply {
            setOnClickListener {
                println("click")
            }
        }

        fun newFragment(id: Int, name: String, age: Int) = Fragment().apply {
            arguments?.putInt("id", id)
            arguments?.putString("name", name)
            arguments?.putInt("age", age)
        }


    }
}

fun main() {
    var s: String = "kotlin"
    val sum = s.sumBy { it.toInt() }
    println(sum)
    //    根据Kotlin中的约定，即当函数中只有一个函数作为参数，并且您使用了lambda表达式作为相应的参数，则可以省略函数的小括号()。故而我们可以写成：
//    s.sumBy( { it.toInt() } )


//    伪代码
//    fun toBeSynchronized() = sharedResource.operation()
//    val result = lock(lock, ::toBeSynchronized)
//    ::toBeSynchronized即为对函数toBeSynchronized()的引用
//    上面的写法也可以写作：
//    val result = lock(lock, {sharedResource.operation()} )

//    还有一个约定，即当函数的最后一个参数是一个函数，并且你传递一个lambda表达式作为相应的参数，则可以在圆括号之外指定它。故而上面例2中的代码我们可写成：
//    val result = lock(lock){
//        sharedResource.operation()
//    }

    testResult()


//    TODO("test todo err")

    testRun1()

//    因为run函数执行了我传进去的lambda表达式并返回了执行的结果，所以当一个业务逻辑都需要执行同一段代码而根据不同的条件去判断得到不同结果的时候
    val index = 3
    val num = run {
        when (index) {
            0 -> "kotlin"
            1 -> "java"
            2 -> "php"
            3 -> "javaScript"
            else -> "none"
        }
    }.length
    println("num = $num")

//    可以使用this关键字，因为在这里它就代码str这个对象，也可以省略
    s.run {
        println(this.length)
        println(first())
        println(last())
    }

    with(s) {
        println(this.length)
        println(first())
        println(last())
    }

    val newStr: String? = "kotlin"
//    val newStr : String? = null

    with(newStr) {
        println("length = ${this?.length}")
        println("first = ${this?.first()}")
        println("last = ${this?.last()}")
    }

    newStr?.run {
        println("length = $length")
        println("first = ${first()}")
        println("last = ${last()}")
    }
//    当我们使用对象可为null时，使用T.run()比使用with()函数从代码的可读性与简洁性来说要好一些。
//    当然关于怎样去选择使用这两个函数，就得根据实际的需求以及自己的喜好了


//    上面的实例我们可以看出，他们的区别在于，T.also中只能使用it调用自身,而T.apply中只能使用this调用自身。
//    因为在源码中T.also是执行block(this)后在返回自身。而T.apply是执行block()后在返回自身。这就是为什么在一些函数中可以使用it,
//    而一些函数中只能使用this的关键所在
    "kotlin".also {
        println("result ${it.plus("-java")}")
    }.also {
        println("result ${it.plus("-php")}")
    }

    "kotlin".apply {
        println("result ${this.plus("-java")}")
    }.apply {
        println("result ${this.plus("-php")}")
    }

    "kotlin".let {
        println("ori:$it")
        it.reversed()
    }.let {
        println(it)
        it.plus("-java")
    }.let {
        println(it)
    }

    "kotlin".also {
        println("ori:$it")
        it.reversed()
    }.also {
        println(it)
        it.plus("-java")
    }.also {
        println(it)
    }

    "kotlin".apply {
        println("ori:$this")
        this.reversed()
    }.apply {
        println(this)
        this.plus("-java")
    }.apply {
        println(this)
    }

    val r1 = "kotlin".takeIf {
        it.startsWith("ko")
    }
    println(r1)

    val r2 = "kotlin".takeUnless {
        it.startsWith("ko")
    }
    println(r2)

    repeat(5){
        println(it+1)
    }
}

private fun resultByOpt(num1: Int, num2: Int, result: (Int, Int) -> Int): Int {
    return result(num1, num2)
}

private fun testResult() {
    val result1 = resultByOpt(1, 2) { num1, num2 ->
        num1 + num2
    }
    val result2 = resultByOpt(1, 2) { num1, num2 ->
        num1 - num2
    }
    val result3 = resultByOpt(1, 2) { num1, num2 ->
        num1 * num2
    }
    val result4 = resultByOpt(1, 2) { num1, num2 ->
        num1 / num2
    }

    println(result1)
    println(result2)
    println(result3)
    println(result4)
}

//当我们需要执行一个代码块的时候就可以用到这个函数,并且这个代码块是独立的。即我可以在run()函数中写一些和项目无关的代码，因为它不会影响项目的正常运行
private fun testRun1() {
    val str = "kotlin"

    run {
        val str = "kotlin"
        println(str)
    }
}