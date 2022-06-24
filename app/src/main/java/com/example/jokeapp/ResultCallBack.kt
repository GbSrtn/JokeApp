package com.example.jokeapp

interface ResultCallBack {
    fun provideSuccess(data: Joke)

    fun provideError(error: JokeFailure)
}