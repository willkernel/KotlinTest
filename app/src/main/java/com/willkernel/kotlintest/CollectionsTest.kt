package com.willkernel.kotlintest

/**
 * Created by willkernel
 * on 2019/3/27.
 */
fun main() {
    val arr = arrayOf("1", 2, 3, 4)

    println(arr.component1())
    println(arr.component3())

// 程序崩溃，因为元素只有4个，所以在不确定元素个数的情况，慎用这些函数，还是使用遍历安全些。
//    println(arr.component5())

    arr.reverse()
    for (any in arr) {
        println(any)
    }

    println("集合类型")
    val list1 = listOf(1, 2, "3", 4, "5")
    val list2 = listOf<String>("1", "2", "3")
    val list3 = listOf(arr)
//    以下代码是错误的。因为List<E>只能是不可变集合。而add、remove、clear等函数时MutableList中的函数
//    list1.add(1)
    for (any in list1) {
        println(any)
    }

//    使用mutableListOf()初始化可变的List类型集合
    val mutableList1 = mutableListOf(1, 2, "3", 4, "5")
    val mutableList2 = mutableListOf<String>("1", "2", "3")
    val mutableList3 = mutableListOf(arr)
    mutableList1.add("6")
    mutableList1.add("7")
    mutableList1.add(8)
    for (any in mutableList1) {
        println("mutableList1 $any")
    }

//    声明并初始化Set的集合：使用setOf(..)函数
//    声明并初始化MutableSet的集合：使用mutableSetOf(..)函数
    val set1 = setOf(1, 2, "3", "4", "6", 1, 2, 3, 4, 5)
    val mutableSet1 = mutableSetOf(1, 2, "3", "4", "2", 1, 2, 3, 4, 5)
    val mutableSet2: HashSet<String>  // 这里的HashSet<>和Java里面的HashSet<>一致
    // 遍历
    for (value in set1) {
        print("set1 $value")//Set类型集合会把重复的元素去除掉
    }


//    不可变的Map类型集合的初始化使用：mapOf()函数
//    可变的Map类型集合的初始化使用：mutableMapOf()函数
    val m1 = mapOf("k1" to 2, "k2" to 3)
    val m2 = mapOf<Int, String>(1 to "v1", 2 to "v2")
    val m3 = mutableMapOf("k1" to 2, "k2" to 3)
    val m4 = hashMapOf("k1" to "v1", "k2" to "v2")
    m2.forEach {
        println("${it.key}  ${it.value}")
    }

    val map = mapOf("key1" to 2, "key1" to 3, "key1" to "value1", "key2" to "value2")

    map.forEach { key, value ->
        println("$key \t $value")
    }


// 注意：Any是kotlin中的超类，故而Student类也是继承自Any的。这里你可以换成Person类结果是相同的
    var listPerson: List<Any>
    var listStudent = listOf(Student("张三", 12, "一班"), Student("王五", 20, "二班"))
    listPerson = listStudent
    listPerson.forEach {
        println(it)
    }

    var mutableListPerson: MutableList<Pers>
    var mutableListStudent = listOf(Student("张三", 12, "一班"), Student("王五", 20, "二班"))
    mutableListPerson = mutableListStudent.toMutableList()//源码中我们可以看出：其实是实例化了一个ArrayList
    mutableListPerson.add(Pers("a", 10))
    mutableListPerson.add(Pers("ba", 22))
    mutableListPerson.forEach {
        println(it)
    }

    //操作符
    operators()
}

fun operators() {
    list2Ary()
    ary2List()
    set2List()

    //元素操作符
    yuansu()

    sortOperation()

    mapOperation()

    filterOperation()

    produceOperation()

    statisticsOperation()
}

fun statisticsOperation() {
    val list1 = listOf(1, 2, 3, 4, 5)
    println("  ------   any -------")
    println(list1.any())
    println(list1.any { it > 10 })

    println("  ------   all -------")
    println(list1.all { it > 2 })

    println("  ------   none -------")
    println(list1.none())
    println(list1.none { it > 2 })

    println("  ------   max -------")
    println(list1.max())
    println(list1.maxBy { it + 2 })

    println("  ------   min -------")
    println(list1.min())        // 返回集合中最小的元素
    println(list1.minBy { it + 2 })

    println("  ------   sum -------")
    println(list1.sum())
    println(list1.sumBy { it + 2 })
    println(list1.sumByDouble { it.toDouble() })

    println(" ------  average -----")
    println(list1.average())

    println("  ------   reduce  -------")
    println(list1.reduce { result, next ->
        println("result $result  next $next")
        result + next
    })
    println(list1.reduceIndexed { index, result, next ->
        println("index $index  result $result  next $next")
        index + result + next
    })
    println(list1.reduceRightIndexed { index, result, next ->
        println("index $index  result $result  next $next")
        index + result + next
    })
    println(list1.reduceRight { result, next ->
        println("result $result  next $next")
        result + next
    })
    for (index in list1.indices) {
        println("index $index")
    }

    fun repeat(times: Int, action: (Int, Int) -> Unit) {

        for (index in 0 until times) {
            action(index, index)
        }
    }

    repeat(3) { n1, n2 ->
        println(n1 + n2 + 1)// index=0,1,2
    }

}

fun produceOperation() {
    println("===produceOperation===")
    var list1 = listOf(1, 2, 3, 4)
    val list2 = listOf("kotlin", "Android", "Java", "PHP", "JavaScript")
    println(list1.plus(list2))
    println(list1 + list2)//合并两个集合中的元素，组成一个新的集合。也可以使用符号+

    println(list1.zip(list2))//由两个集合按照相同的下标组成一个新集合。该新集合的类型是：List<Pair>
    println(list1.zip(list2) { t1, t2 ->
        // 组成的新集合由元素少的原集合决定
        t1.toString().plus("-").plus(t2)
    })

    val list = listOf(Pair(1, "Kotlin"), Pair(2, "Android"), Pair(3, "Java"), Pair(4, "PHP"))
    println(list.unzip())//和zip的作用相反。把一个类型为List<Pair>的集合拆分为两个集合

    println(list2.partition {
        // 判断元素是否满足条件把集合拆分为两个Pair组成的新集合
        it.startsWith("Ja")
    })
}

fun filterOperation() {
    val list1 = listOf(-1, -3, 1, 3, 5, 6, 7, 2, 4, 10, 9, 8)
    val list2 = listOf(1, 3, 4, 5, null, 6, null, 10)
    val list3 = listOf(1, 1, 5, 2, 2, 6, 3, 3, 7, 4, 4, 8)

    println("===filter===")
    println(list1.filter { it > 1 })
    println(list1.filterIndexed { index, result -> result in 4..6 })
    println(list1.filterNot { it > 1 })
    println(list2.filterNotNull())

    println("===take===")
    println(list1.take(4))
    println(list1.takeWhile { it < 5 })//不满足条件 丢弃
    println(list2.takeLast(3))
    println(list1.takeLastWhile { it > 3 })

    println("===drop===")
    println(list1.drop(3))
    println(list1.dropWhile { it < 5 })//满足条件丢弃
    println(list1.dropLast(3))
    println(list1.dropLastWhile { it > 6 })

    println("===distinct ===")
    println(list3.distinct())
    println(list3.distinctBy { it + 2 })

    println("===slice  ===")
    println(list1.slice(listOf(1, 2, 3))) //返回对应下标的元素集合
    println(list1.slice(IntRange(1, 3)))

}

fun mapOperation() {
    val list = listOf("kotlin", "Android", "Java", "PHP", "Python", "IOS")
    println(list.map { "str-".plus(it) })
    println(list.mapNotNull { "str-".plus(it) })
    println(list.mapIndexed { index, str -> index.toString().plus("-").plus(str) })
    println(list.mapIndexedNotNull { index, str -> index.toString().plus("-").plus(str) })
    println(list.flatMap { listOf(it, "new ".plus(it), "last ".plus(it)) })
    println(list.groupBy { if (it.startsWith("Java")) "big" else "latter" })
}

fun sortOperation() {
    val list = listOf(-3, 1, 2, 4, -2, -1, 6)
    println(list.reversed())
    println(list.sorted())
    println(list.sortedBy { it > 0 })
    println(list.sortedDescending())
    println(list.sortedByDescending { it % 2 == 0 })
}

fun yuansu() {
    val list = listOf("kotlin", "Android", "Java", "PHP", "Python", "IOS")

    println("  ------   contains -------")
    println(list.contains("JS"))

    println("  ------   elementAt -------")

    println(list.elementAt(2))
    println(list.elementAtOrElse(10) { it })
    println(list.elementAtOrNull(10))

    println("  ------   get -------")
    println(list.get(2))
    println(list.getOrElse(10, { it }))
    println(list.getOrNull(10))

    println("  ------   first -------")
    println(list.first())
    println(list.first { it == "Android" })
    println(list.firstOrNull())
    println(list.firstOrNull { it == "Android" })

    println("  ------   last -------")
    println(list.last())
    println(list.last { it == "Android" })
    println(list.lastOrNull())
    println(list.lastOrNull { it == "Android" })

    println("  ------   indexOf -------")
    println(list.indexOf("Android"))
    println(list.indexOfFirst { it == "Android" })
    println(list.indexOfLast { it == "Android" })

    println("  ------   single -------")
    val list2 = listOf("list", "last")
//    println(list2.single())     // 只有当集合只有一个元素时，才去用这个函数，不然都会抛出异常。
    println(list2.single { it == "list" }) //当集合中的元素满足条件时，才去用这个函数，不然都会抛出异常。若满足条件返回该元素
    println(list2.singleOrNull()) // 只有当集合只有一个元素时，才去用这个函数，不然都会返回null。
    println(list2.singleOrNull { it == "list" }) //当集合中的元素满足条件时，才去用这个函数，不然返回null。若满足条件返回该元素

    println("  ------   forEach -------")
    list.forEach { println(it) }
    list.forEachIndexed { index, it -> println("index : $index \t value = $it") }

    println("  ------   componentX -------")
    println(list.component1())  // 等价于`list[0]  <=> list.get(0)`
    println(list.component2())  // 等价于`list[1]  <=> list.get(1)`
    println(list.component3())  // 等价于`list[2]  <=> list.get(2)`
    println(list.component4())  // 等价于`list[3]  <=> list.get(3)`
    println(list.component5())  // 等价于`list[4]  <=> list.get(4)`
}

fun set2List() {
    val set = setOf(1, 2)
    val l = set.toList()
    println(set.javaClass.toString())//class java.util.LinkedHashSet
    println(l.javaClass.toString())//class java.util.ArrayList
    println(l[0])
}

fun ary2List() {
    val ary = arrayOf(1, 2)
    val l = ary.toList()
    println(ary.javaClass.toString())//class [Ljava.lang.Integer;
    println(l.javaClass.toString())//class java.util.Collections$SingletonList
    println(l[0])
}

fun list2Ary() {
    val l = listOf(1, 2, 3)
    val ary = l.toIntArray()
    println(l.javaClass.toString())
    println(ary.javaClass.toString())
    println(ary[1])
}

open class Pers(val name: String, val age: Int) {
    override fun toString(): String {
        return "Person(name='$name', age=$age)"
    }
}

class Student(name: String, age: Int, cls: String) : Pers(name, age)