package com.example.jokeapp

interface CacheDataSource {
    fun addOrRemove(id: String, joke: JokeServerModel) : Joke

}