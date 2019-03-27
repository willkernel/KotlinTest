package com.willkernel.kotlintest

/**
 * Created by willkernel
 * on 2019/3/21.
 * 类创建
 * Kotlin 的写法（如果不想暴露成员变量的set方法，可以将 var 改成 val )
 */
private class Person {
    var name:String?=null
    get() = field
    set(value){field=value}


    var age:Int=0
    get() = field
    set(value){field=value}


}
class Person1(var name:String,var age:Int){
    override fun toString(): String {
        return "Person1(name='$name', age=$age)"
    }
}
class Person2{
    var name:String?=null
    private set
    var age:Int=0
    private set

    constructor(name: String,age: Int)
    constructor(name: String)

    override fun toString(): String {
        return "Person1(name='$name', age=$age)"
    }
}
class Person3(name: String, private var age: Int){
    private var name:String?=name

    override fun toString():String{
        return "$name : $age"
    }
}
