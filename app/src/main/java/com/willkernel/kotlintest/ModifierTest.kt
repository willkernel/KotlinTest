package com.willkernel.kotlintest

/**
在类中声明属性、方法等，可以使用任意修饰符修饰。并且在类中的任意位置都能访问各个修饰符修饰的属性、方法等。
在类外，只能访问由public、internal修饰符修饰的属性、方法。此情况不管是不是在同一个文件中去操作。
在接口中声明的情况下，只能由public修饰符修饰属性。方法可由public、private两个修饰符去修饰，但是，用private修饰符修饰符修饰的方法不能被实现该接口的类重写。
为了使用接口中由public修饰符修饰的属性、方法等。只能定义一个类去实现该接口，然后重写其属性、方法。这样就能在可在类中或类外去操作
 */
class ModifierTest {
    var num1: Int = 2
    internal var num2: Int = 2
    protected var num3: Int = 2
    private var num4: Int = 2

    init {
        num1 = 1
        num2 = 1
        num3 = 1
        num4 = 1
    }
}

interface ITest {
    var num1: Int //只能使用publick
    //    private var num4: Int
//    internal var num2: Int
//    protected var num3: Int
    private fun test(): Int {
        return num1
    }
}

class TestInterface : ITest {
    override var num1: Int
        get() = 3
        set(value) {}

//    override fun test():Int{
//
//    }//不能重写


}

private class TestConstructor constructor(num: Int) {
    private val num1: Int = 0
    internal val num2: Int = 1
    protected val num3: Int = 2
    val num4: Int = 4

    private constructor(num1: Int, num2: Int) : this(num1) {
//        private val n1: Int
//        internal val n2: Int
//        protected val n3: Int //不能使用这些修饰符
        val n4: Int
    }
}

public fun testFun1() {}
//protected fun testFun2() {}//顶层声明不能使用protected
private fun testFun3() {}

internal fun testFun4() {}
fun main() {
    testFun1()
    testFun3()
    testFun4()

    println("num1=> " + ModifierTest().num1)
    println("num2=> " + ModifierTest().num2)
//    println("num3=> "+ModifierTest().num3) 不能访问
//    println("num4=> "+ModifierTest().num4) 不能访问

    println("num2=> " + TestInterface().num1)
//    println("num2=> " + TestInterface().test())

    /*在局部声明的情况下，只能使用public修饰符修饰的变量。并且在变量只能在局部这个范围内使用，出了这个范围后就访问不到了*/
    fun test(){
        var n2:Int=3
//        private var n1:Int=2
//        internal var n3:Int=4
//        protected var n4:Int=5  //不能使用这些修饰符
    }


}
