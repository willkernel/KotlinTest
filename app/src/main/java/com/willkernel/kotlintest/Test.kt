package com.willkernel.kotlintest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import java.util.*

/**
 * Created by willkernel
 * on 2019/3/21.
 */
//需要注意的是要把静态变量定义在类上方
const val CONSTANT = "constant"
const val TAG = "Test"

class Test : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = this@Test
        var info = "toast info"
        Toast.makeText(context, info, Toast.LENGTH_LONG).show()
        var text:TextView = findViewById(R.id.text)
        var intent = Intent(context, MainActivity::class.java)
//        Caused by: android.os.TransactionTooLargeException: data parcel size 1049004 bytes
        val data=ByteArray(1024*1024)
        intent.putExtra("data",data)

        text.postDelayed({ startActivity(intent) }, 2000)
        test("start activity")
    }

    //    fun test(message: String):Unit{
    fun test(message: String) {
        System.err.println(message)
    }

    fun log(str: String) {
        Log.e(TAG, str)
    }

    override fun onResume() {
        super.onResume()
        test("onResume")
        var i = 1
        var long = 2
        var b: Boolean = true
        var a = true
        var f = 0f
        var d = 0.0
        var c = 'A'
        var s = "str"
        var slash = "\n"

        if ("" is String) {
            log("is str")
            log("hello${s}")
            log("hello$s")
            log("hello\$d")
            log("hello{\$slash}")
        }

        var s1 = "text"
        var s2 = "text"
        log("equals " + (s1 == s2))

        var ary1 = intArrayOf(1, 2, 3)
        var ary2 = floatArrayOf(4f, 5f, 6f)
        var ary3 = arrayOf("1", "2", 3)
        var ary4 = arrayListOf("11", "12", "13")

        log("ary3 ${Arrays.toString(ary3)}")
        log("ary4 $ary4")

        for (any in ary3) {
            log(any.toString())
        }

        for (s in ary4) {
            log(s)
        }
//        角标循环
        for (index in ary4.indices) {
            log("$index ${ary4[index]}")
        }
//        区间
        for (i in IntRange(1, ary4.size - 1)) {
            log("$i ${ary4[i]}")
        }
//        比上面简洁
        for (i in 1..ary4.size - 1) {
            log("$i ${ary4[i]}")
        }

        for (i in 1 until ary4.size - 1) {
            log("$i ${ary4[i]}")
        }

        //对应 switch case
        var count = 1
        when (count) {
            1 -> {
                println("hello")
            }
            2 -> {
                println("my")
            }
            in 3..4 -> {
                print("dream")
            }
            else -> {
                println("world")
            }
        }
        count = 3
        when (count) {
            1 -> println("android")
            in 2..5 -> println("count")
            else -> println("else")
        }
    }
}