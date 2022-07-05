package com.example.jokeapp

interface JokeCallback {
    fun provide(joke: JokeUiModel)
}