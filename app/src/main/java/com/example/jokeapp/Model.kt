package com.example.jokeapp

interface Model {
    fun getJoke()

    fun init(callback: JokeCallBack)

    fun clear()
}