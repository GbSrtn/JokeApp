package com.example.jokeapp.Base

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

//class BaseModel(
//    private val service: JokeService,
//    private val resourseManager: ResourseManager
//) : Model {
//    private var callback: ResultCallBack? = null
//    private val noConnection by lazy { NoConnection(resourseManager) }
//    private val serviceUnavailable by lazy { ServiceUnavailable(resourseManager) }
//
//    override fun getJoke() {
//        service.getJoke().enqueue(object : retrofit2.Callback<JokeDTO>{
//            override fun onResponse(call: Call<JokeDTO>, response: Response<JokeDTO>) {
//                if (response.isSuccessful) {
//                    callback?.provideSuccess(response.body()!!.getJoke())
//                } else {
//                    callback?.provideError(serviceUnavailable)
//                }
//            }
//
//            override fun onFailure(call: Call<JokeDTO>, t: Throwable) {
//                if (t is UnknownHostException) {
//                    callback?.provideError(noConnection)
//                } else {
//                    callback?.provideError(serviceUnavailable)
//                }
//            }
//
//        })
//    }
//
//    override fun init(callback: ResultCallBack) {
//        this.callback = callback
//    }
//
//    override fun clear() {
//        callback = null
//    }
//
//}