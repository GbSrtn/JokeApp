package com.example.jokeapp

interface Model {

    fun init(callback: JokeCallback)

    fun changeJokeStatus(jokeCallback: JokeCallback)

    fun getJoke()

    fun clear()
}