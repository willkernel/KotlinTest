package com.willkernel.kotlintest

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item.view.*

class LambdaTest : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_lambda)
        var context = this@LambdaTest
        var btn: Button = findViewById(R.id.btn)
        btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(context, "onclick", Toast.LENGTH_SHORT).show()
            }
        })
        btn.setOnClickListener { Toast.makeText(context, "lambda click", Toast.LENGTH_SHORT).show() }


        val rv: RecyclerView = findViewById(R.id.recyclerView)
        val list = arrayListOf("1", "2", "3")
        val adapter = TestAdapter(context, list)
        adapter.setOnItemClickListener { position, item ->
            Toast.makeText(context, "click $position  $item", Toast.LENGTH_SHORT).show()
        }
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = adapter
    }
}

fun main() {
    //无参数的情况
    fun test() {
        println("no params")
    }

    val test1 = { println("no params") }
    test1()

    //有参数的情况 (Int,Int)->Int={a,b->a+b}
    val test2: (Int, Int) -> Int = { a, b -> a + b }
    //简化 {(a:Int,b:Int)->a+b}
    val test3 = { a: Int, b: Int -> a + b }
    println(test3(3, 5))

    //lambda表达式作为函数中的参数
    fun test(a: Int, b: Int): Int {
        return a + b
    }

    fun sum(num: Int, num1: Int): Int {
        return num + num1
    }

    println(test(10, sum(20, 30)))

    fun test(a: Int, b: (num1: Int, num2: Int) -> Int): Int {
        return a + b.invoke(3, 5)
    }
    println(test(10) { num1: Int, num2: Int -> num1 + num2 })

//    it
//    it并不是Kotlin中的一个关键字(保留字)。
//    it是在当一个高阶函数中Lambda表达式的参数只有一个的时候可以使用it来使用此参数。it可表示为单个参数的隐式名称，是Kotlin语言约定的
    val it: Int = 0  // 即it不是`Kotlin`中的关键字。可用于变量名称
    val arr = arrayOf(1, 3, 5, 7, 9)
    println(arr.filter { it < 5 }.component1())

    //bool 的值为lambda表达式 ，在后面的调用中需要使用lambda实现，不能直接传参数
    //可以使用bool.invoke(Int) 也可以直接使用 () 传参
    fun testA(num1: Int, bool: (Int) -> Boolean): Int {
        return if (bool(num1)) num1 else 0
    }

    println("testA ${testA(3) { num: Int -> num > 2 }}")
    println("testA ${testA(2) { it > 2 }}")


//    下划线（_）
//    在使用Lambda表达式的时候，可以用下划线(_)表示未使用的参数，表示不处理这个参数
    val map = mapOf<String, String>("k1" to "v1", "k2" to "v2")
    map.forEach { key, value -> print("$key  $value") }
    map.forEach { _, value -> println(value) }


    //    匿名函数
//匿名函数的特点是可以明确指定其返回值类型。
//它和常规函数的定义几乎相似。他们的区别在于，匿名函数没有函数名
    val sum1 = fun(x: Int, y: Int): Int {
        return x + y
    }
    println(sum1(2, 5))

    val n1 = fun(x: Int, y: Int) = x + y
    val n2 = fun(x: Int, y: Int): Int = x + y
    val n3 = fun(x: Int, y: Int): Int {
        return x + y
    }
    println(n1(1, 2))
    println(n1(3, 4))
    println(n1(5, 6))


//    提供了指定的接受者对象调用Lambda表达式的功能。在函数字面值的函数体中，可以调用该接收者对象上的方法而无需任何额外的限定符。
//    它类似于扩展函数，它允你在函数体内访问接收者对象的成员
    val iop = fun Int.(other: Int): Int = this + other
//    匿名函数作为接收者类型
//    匿名函数语法允许你直接指定函数字面值的接收者类型，如果你需要使用带接收者的函数类型声明一个变量，并在之后使用它，这将非常有用
    print(2.iop(3))


//    让函数返回一个函数，并携带状态值
    val t = test(3)
    println(t())//4+3
    println(t())//5+3
    println(t())//6+3

    //引用外部变量，并改变外部变量的值
    var sum: Int = 0
    val a = arrayOf(1, 3, 5, 7, 9)
    a.filter { it < 7 }.forEach { sum += it }
    println(sum)
}

class HTML {
    fun body() {

    }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()// 创建接收者对象
    html.init()   // 将该接收者对象传给该 lambda
    return html
}

fun t1() {
    fun t2() {

    }
}

//携带状态
fun test(b: Int): () -> Int {
    var a = 3
    return fun(): Int {
        a++
        return a + b
    }
}

//在Android开发中为RecyclerView的适配器编写一个Item点击事件
class TestAdapter(val context: Context, val data: MutableList<String>) :
    RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    private var listener: ((Int, String) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapter.TestViewHolder {
        return TestViewHolder(View.inflate(context, R.layout.layout_item, null))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TestAdapter.TestViewHolder, position: Int) {
//        holder.itemView.item_tv.run {
//           text = data[position]
//           textSize = 16f
//           gravity=Gravity.CENTER
//        }
        with(holder.itemView.item_tv) {
            text = data[position]
            textSize = 16f
            gravity = Gravity.CENTER
        }
        holder.itemView.layoutParams =
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        holder.itemView.setOnClickListener {
            listener?.invoke(position, data[position])
        }
    }


    inner class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    //    fun setOnItemClickListener(listener: ((Int,String)->Unit)?){
    fun setOnItemClickListener(listener: ((position: Int, item: String) -> Unit)?) {
        this.listener = listener
    }

}