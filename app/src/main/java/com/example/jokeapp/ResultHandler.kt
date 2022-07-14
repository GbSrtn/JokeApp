package com.example.jokeapp

interface ResultHandler<S, E> {
    fun handleResult(result: Result<S, E>) : JokeUiModel
}