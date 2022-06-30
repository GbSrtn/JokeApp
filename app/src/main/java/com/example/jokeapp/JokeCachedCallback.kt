package com.example.jokeapp

interface JokeCachedCallback {
    fun provide(jokeServerModel: JokeServerModel)

    fun fail()
}