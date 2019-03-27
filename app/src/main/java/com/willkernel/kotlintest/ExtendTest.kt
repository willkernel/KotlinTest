package com.willkernel.kotlintest

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Created by willkernel
 * on 2019/3/25.
 */

abstract class Class1 {
    abstract var num: Int
    abstract fun test()
}

open class Class2 {
    open var num: Int = 1
    open fun foo() = "foo"
    open fun bar() = "bar"
}

class C1 : Class2() {

}

//无主构造函数
//当实现类无主构造函数时，则每个辅助构造函数必须使用super关键字初始化基类型，或者委托给另一个构造函数。 请注意，在这种情况下，不同的辅助构造函数可以调用基类型的不同构造函数
class MyViewTest : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}

//存在主构造函数
//当存在主构造函数时，主构造函数一般实现基类型中参数最多的构造函数，参数少的构造函数则用this关键字引用即可了
class MyViewTest1(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
}

//函数的重载与重写
open class Demo1 {
    fun test() {}   // 注意，这个函数没有用open修饰符修饰
}

class DemoTest : Demo1() {

    // 这里声明一个和基类型无open修饰符修饰的函数，且函数名一致的函数
    // fun test(){}   编辑器直接报红，根本无法运行程序
    // override fun test(){}   同样报红
}

open class A {
    open var num: Int = 1
    open val num1: Int = 2
    open fun foo() {}
}

// B这个类继承类A，并且类B同样使用open修饰符修饰了的
open class B : A() {

    // 这里使用final修饰符修饰该方法，禁止覆盖掉类A的foo()函数
    final override fun foo() {}

    //  方法的重载其实主要体现在这个地方。即函数名相同，函数的参数不同的情况。这一点和Java是相同的
    fun foo(num: Int) {

    }

    override var num = 2 //重写属性
        set(value) {
            field = value
        }
        get() = super.num //实际的项目中在重写属性的时候不用get() = super.xxx,因为这样的话，不管你是否重新为该属性赋了新值，还是支持setter(),在使用的时候都调用的是基类中的属性值
    //    override val num = 3 //父类是var 不能使用val
    override var num1 = 3
//    override val num1 = 3 //父类是val 可以使用var,val ,不能有setter
}

open class Demo {
    open var num = 1
    open val valStr = "我是用val修饰的属性"
}

class Demo2(override var num: Int = 1, override val valStr: String) : Demo() {
    override fun toString(): String {
        return "Demo2(num=$num, valStr='$valStr')"
    }
}

/**覆盖规则
这里的覆盖规则，是指实现类继承了一个基类，并且实现了一个接口类，当我的基类中的方法、属性和接口类中的函数重名的情况下，怎样去区分实现类到底实现哪一个中的属性或属性。
这一点和一个类同时实现两个接口类，而两个接口都用同样的属性或者函数的时候是一样的*/
open class C {
    open fun test1() {
        print("c test1")
    }

    open fun test2() {
        print("c test2")
    }
}

interface D {
    fun test1() {
        print("interface D test1")
    }

    fun test2() {
        print("interface D test2")
    }
}

class E : C(), D {
    override fun test1() {
        super<C>.test1()
        super<D>.test1()
    }

    override fun test2() {
        super<C>.test2()
        super<D>.test2()
    }
}

fun main() {
    print(C1().num)
    print(C1().foo())
    print(C1().bar())
    print(B().num) //重写属性的时候慎用super关键字
    B().num1 = 1
    print(Demo2(2, "test"))
    print(E().test1())
    print(E().test2())
}