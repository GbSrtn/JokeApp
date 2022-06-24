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



