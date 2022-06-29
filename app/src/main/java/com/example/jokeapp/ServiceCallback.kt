package com.example.jokeapp

interface ServiceCallback {
    fun returnSuccess(data: JokeServerModel)

    fun returnError(type: ErrorType)
}