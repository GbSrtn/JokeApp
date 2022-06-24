package com.example.jokeapp

interface ServiceCallback {
    fun returnSuccess(data: JokeDTO)

    fun returnError(type: ErrorType)
}