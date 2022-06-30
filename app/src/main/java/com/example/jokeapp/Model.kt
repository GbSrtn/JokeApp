package com.example.jokeapp

interface Model {

    fun init(callback: JokeCallback)

    fun changeJokeStatus(jokeCallback: JokeCallback)

    fun chooseDataSource(cached : Boolean)

    fun getJoke()

    fun clear()
}