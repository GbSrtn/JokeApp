package com.example.jokeapp

import android.util.Log

class ViewModel(private val model: Model) {

    private var dataCallback: DataCallBack? = null

    fun init(callback: DataCallBack) {
        this.dataCallback = callback
        model.init(object : JokeCallBack {
            override fun provide(joke: Joke) {
                dataCallback?.let {
                    joke.map(it)
                }
            }
        })
    }

    fun getJoke() {
        model.getJoke()
    }

    fun clear() {
        dataCallback = null
        model.clear()
    }
}



