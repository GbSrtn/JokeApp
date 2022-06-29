package com.example.jokeapp

interface CloudDataSource {
    fun getJoke(callback: JokeCloudCallback)
}
