package com.willkernel.kotlintest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.Toast
import java.nio.file.Files.find

class MainActivity : AppCompatActivity() {
    var number = 0

    //    构造代码块
    init {
        number = 1
    }

    //    静态代码块
    companion object {
        var number = 5

        init {
            number++
        }
    }

//    方法代码块
    fun methodBlock() {
        run { var a = 1 }
    }
    //声明组件
    private lateinit var mTabLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTabLayout=findViewById(R.id.tab_layout)
        toast(text = number.toString())
        toast(text = MainActivity.number.toString())
        var p = Person1("wk", 12)
        p.name = "Aty"
        p.age = 24
        println("name:${p.name},age:${p.age}")

        var p2 = Person2("kotlin", 14)
        println("name> ${p2.name}")

        var p3 = Person3("person", 23)
        println(p3)

//        匿名内部类
        object : Callback {
            override fun onSuccess() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFail() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        NewTask().show()

        toast(text = "text")
        toast(this)
        toast(this, "弹个吐司", Toast.LENGTH_LONG)

        toast(text = add(1, 3, 5).toString())

        ToastUtil.show()
//        show()

//        ToastUtil.sToast= Toast(this@MainActivity)
//        ToastUtil.showToast()

    }

    var name = "what"

    //    内部类 不使用inner 访问不到外部变量
    class MyTask {
        var name = "wk"
        fun show() {
//            println(name+"---"+this@MainActivity.name)
        }
    }

    inner class NewTask {
        var name = "wk"
        fun show() {
            println(name + "---" + this@MainActivity.name)
        }
    }

    //    在 Kotlin 将这种方式称之为伴生对象 ,静态代码块只允许一个，静态变量 静态方法
//    companion object ToastUtils {
//
//        var sToast: Toast? = null
//
//        fun show() {
//            sToast!!.show()
//        }
//    }

    fun toast(context: Context = this, text: String = "def", duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, text, duration).show()
    }

    fun add(vararg array: Int): Int {
        var count = 0
        array.forEach { count += it }
        return count
    }
}
