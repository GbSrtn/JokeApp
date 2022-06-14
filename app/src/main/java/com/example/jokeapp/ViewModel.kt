package com.example.jokeapp

import android.util.Log

class ViewModel(private val model: Model) {

    private var callback: TextCallBack? = null

    fun init(callback: TextCallBack) {
        this.callback = callback
        model.init(object : ResultCallBack {
            override fun provideSuccess(data: Joke) = callback.provideText(data.getJokeUI())
            override fun provideError(error: JokeFailure) = callback.provideText(error.getMessage())
        })
    }

    fun getJoke() {
        model.getJoke()
    }

    fun clear() {
        callback = null
        model.clear()
    }
}

interface TextCallBack {

    fun provideText(text:String)
}

interface Model {
    fun getJoke()

    fun init(callback: ResultCallBack)

    fun clear()
}

interface ResultCallBack {
    fun provideSuccess(data: Joke)

    fun provideError(error: JokeFailure)
}

class TestModel(resourseManager: ResourseManager) : Model {

    private var callback: ResultCallBack? = null
    private var count = 0
    private val noConnection = NoConnection(resourseManager)
    private val serviceUnavailable = ServiceUnavailable(resourseManager)

    override fun getJoke() {
        Thread {
            Thread.sleep(1000)
            when (count) {
                0 -> callback?.provideSuccess(Joke("testText", "testPunchline"))
                1 -> callback?.provideError(noConnection)
                2 -> callback?.provideError(serviceUnavailable)
            }
            count++
            if (count == 3) count = 0
        }.start()
    }

    override fun init(callback: ResultCallBack) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }
}
