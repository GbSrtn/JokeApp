package com.example.jokeapp

import android.util.Log

class Tesst {
    var g = 0

    val a = Test2(object :doSmt{
        override fun doq(s: Int) {
            
        }
    })

}

class Test2(val doq: doSmt){

    fun hehe(){
        Log.d("sdf", "hehe: ")
        doq.doq(12)
    }

}


interface doSmt{
  fun doq(s:Int)
}