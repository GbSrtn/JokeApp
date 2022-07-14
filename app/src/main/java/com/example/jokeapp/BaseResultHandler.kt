package com.example.jokeapp

abstract class BaseResultHandler<S, E>
    (private val jokeDataFetcher: JokeDataFetcher<S, E>) : ResultHandler<S, E> {

    suspend fun process() : JokeUiModel {
        return handleResult(jokeDataFetcher.getJoke())
    }
}