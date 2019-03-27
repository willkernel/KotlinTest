package com.willkernel.kotlintest

/**
 * Created by willkernel
 * on 2019/3/25.
 */
const val CONST_NUM = 5
const val CONST_STR = "Kotlin"

class Mime {
    val id: String = "123"
    var name: String? = "kotlin"
    var age: Int? = 22
    var sex: String? = "man"
    var weight: Float? = 120f

    private var test: String = ""
        get() = "123"
        set(value) {
            field = value
        }

    // getter 和 setter是可选的
    // 当用var修饰时，必须为属性赋默认值(特指基本数据类型，因为自定义的类型可以使用后期初始化属性，见后续)
    // 即使在用getter()的情况下,不过这样写出来，不管我们怎么去改变其值，其值都为`123`
    var test1: String = ""
        get() = "test1"
        //    在上面的代码中出现了set(value){field = value}这样的一句代码。其中value是Koltin写setter()函数时其参数的约定俗成的习惯。
//    你也可以换成其他的值。而field是指属性本身
        set(value) {
            field = value
        }

    // 用val修饰时，用getter()函数时，属性可以不赋默认值。但是不能有setter()函数。
    val test2: String
        get() = "test2"  // 等价于：val test2 : String = "123"
}


/**
使用了val修饰的属性，不能有setter().
不管是val还是var修饰的属性，只要存在getter(),其值再也不会变化
使用var修饰的属性，可以省略掉getter(),不然setter()毫无意义。当然get() = field除外。而get() = field是Koltin默认的实现，是可以省略这句代码的
 */
class Mime1 {
    private val size = 0
    val isEmpty: Boolean
        get() = this.size == 0
    val num = 2
        get() = if (field > 5) 10 else 0

    var str = "test"
        get() = field
        set(value) {
            field = if (value.isNotEmpty()) value else "null"
        }

    var str1 = ""
        get() = "ori"
        set(value) {
            field = if (value.isNotEmpty()) value else "null"
        }
}
//属性访问器的可见性修改为private或者该属性直接使用private修饰时，我们只能手动提供一个公有的函数去修改其属性了。就像Java中的Bean的setXXXX()
//var str1 = "kotlin_1"
//    private set         // setter()访问器的私有化，并且它拥有kotlin的默认实现
//
//var test : String?
//    @Inject set         // 用`Inject`注解去实现`setter()`
//
//val str2 = "kotlin_2"
//    private set         // 编译错误，因为val修饰的属性，不能有setter
//
//var str3 = "kotlin_3"
//    private get         // 编译出错，因为不能有getter()的访问器可见性

private var _table: Map<String, Int>? = null
public val table: Map<String, Int>
    get() {
        if (table == null) {
            _table = HashMap<String, Int>()
        }
        return _table ?: throw AssertionError("set to null by another thread")
    }

/**该关键字必须声明在类的主体内，并且只能是用var修饰的属性。并且该属性没有自定义的setter()与getter()函数。
该属性必须是非空的值，并且该属性的类型不能为基本类型*/
class LateInitTest {
    lateinit var user: String
    /*下面的代码是错误的。因为用lateinit修饰的属性，不能为基本类型。
    这里的基本类型指 Int、Float、Double、Short等，String类型是可以的
    */
    // lateinit var num : Int
}

/**所谓延迟初始化即：指当程序在第一次使用到这个变量（属性）的时候在初始化。
声明延迟初始化属性的特点：
使用lazy{}高阶函数，不能用于类型推断。且该函数在变量的数据类型后面，用by链接。
必须是只读变量，即用val声明的变量*/
private val mTitles: Array<String> by lazy {
    arrayOf("1", "2", "3")
}

fun main() {
    val mime = Mime()
    print("id=${mime.id} name=${mime.name} age=${mime.age} sex=${mime.sex} weight=${mime.weight}")
//    mime.id="12" //id是只读的，其他的属性是可读写的 编辑器直接会报错
    mime.name = "android"
    print("id=${mime.id} name=${mime.name} age=${mime.age} sex=${mime.sex} weight=${mime.weight}")

    print("Getter()与Setter()")

    val mime1 = Mime1()
    println("isEmpty = ${mime1.isEmpty}")
    println("num = ${mime1.num}")

    println("str ${mime1.str}  str1 ${mime1.str1}")
    mime1.str = "kotlin"
    mime1.str1 = "android"
    println("str ${mime1.str}  str1 ${mime1.str1}")
}