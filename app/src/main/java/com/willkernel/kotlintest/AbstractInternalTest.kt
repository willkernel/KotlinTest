package com.willkernel.kotlintest

/**
抽象类的规则
在Kotlin中的抽象类在顶层定义的时候只能使用public可见性修饰符修饰。
抽象类中可以定义内部抽象类。
只能继承一个抽象类。
若要实现抽象类的实例化，需要依靠子类采用向上转型的方式处理。
抽象类可以继承自一个继承类，即抽象类可以作为子类。不过，抽象类建议不用open修饰符修饰，因为可以覆写抽象类的父类的函数
 */
abstract class Language {
    val TAG = this.javaClass.simpleName
    fun test() {

    }

    abstract var name: String
    abstract fun init()
}

class TestAbstarctA : Language() {
    override var name: String
        get() = "kotlin"
        set(value) {}

    override fun init() {
        println("我是$name")
    }

}

class TestAbstarctB : Language() {
    override var name: String
        get() = "Java"
        set(value) {}

    override fun init() {
        println("我是$name")
    }
}

class Other {
    val numOther = 1

    /**调用嵌套类的属性或方法的格式为：外部类.嵌套类().嵌套类方法/属性。在调用的时候嵌套类是需要实例化的。
    嵌套类不能使用外部类的属性和成员*/
    class Nested {
        fun init() {

            println("执行了init方法")
        }
    }

    /**调用内部类的属性或方法的格式为：外部类().内部类().内部类方法/属性。在调用的时候嵌套类是需要实例化的。
    内部类不能使用外部类的属性和成员*/
    inner class InnerClass {
        val name = "inner"
//        numOther=1
        fun init() {
            println("我是内部类")
        }
    }

    private lateinit var listener: OnClickListener //后期初始化不能使用在可空对象

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    fun testListener() {
        listener.onClick("匿名inner class ")
    }

//    局部类
//    局部类只能在定义该局部类的方法中使用
//    定义在实例方法中的局部类可以访问外部类的所有变量和方法。但不能修改
//    局部类中的可以定义属性、方法。并且可以修改局部方法中的变量
    fun partMethod(){
        var name : String = "partMethod"

        class Part{
            var numPart : Int = 2

            fun test(){
                name = "test"
                numPart = 5
                println("我是局部类中的方法")
            }
        }

        val part = Part()
        println("name = $name \t numPart = " + part.numPart + "\t numOther = numOther")
        part.test()
        println("name = $name \t numPart = " + part.numPart + "\t numOther = numOther")
    }
}

interface OnClickListener {
    fun onClick(name: String)
}

/**内部类
 *关键字
声明一个内部类使用inner关键字
声明格式：inner class 类名(参数){}
 * */



fun main() {
    var t1 = TestAbstarctA()
    var t2 = TestAbstarctB()
    println(t1.name)
    println(t2.name)
    t1.init()
    t2.init()

    // 若要实现抽象类的实例化，需要依靠子类采用向上转型的方式处理。
    var t3: Language = TestAbstarctB()

    Other.Nested().init()
    Other().InnerClass().init()
    val other = Other()
    other.setOnClickListener(object : OnClickListener {
        override fun onClick(name: String) {
            println(name)
        }
    })
    other.testListener()

    other.partMethod()
}