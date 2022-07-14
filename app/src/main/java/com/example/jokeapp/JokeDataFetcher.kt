package com.example.jokeapp

interface JokeDataFetcher<S, E> {
    suspend fun getJoke(): Result<S, E>
}