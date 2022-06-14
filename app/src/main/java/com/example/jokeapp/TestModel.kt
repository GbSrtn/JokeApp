package com.example.jokeapp

//class TestModel : Model<Any, Any> {
//    private var callback: ResultCallBack<Any, Any>? = null
//
//    private var count = 1
//
//    override fun getJoke() {
//        Thread {
//            Thread.sleep(1000)
//            if (count % 2 == 0) {
//                callback?.provideSuccess("success")
//            } else {
//                callback?.provideError("error")
//            }
//            count++
//        }.start()
//    }
//
//    override fun init(callBack: ResultCallBack<Any, Any>) {
//        this.callback = callback
//    }
//
//    override fun clear() {
//        callback = null
//    }
//}