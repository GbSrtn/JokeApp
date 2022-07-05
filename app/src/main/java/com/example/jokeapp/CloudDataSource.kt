package com.example.jokeapp

interface CloudDataSource {
    suspend fun getJoke() : Result<JokeServerModel, ErrorType>
}
