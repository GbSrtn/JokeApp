package com.example.jokeapp

interface JokeFailureHandler {
    fun handle(e: Exception) : JokeFailure
}