package com.example.jokeapp

interface JokeCachedCallback {
    fun provide(joke: Joke)

    fun fail()
}