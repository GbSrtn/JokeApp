package com.example.jokeapp

import retrofit2.Call
import retrofit2.http.GET

interface JokeService {
    @GET("https://api.chucknorris.io/jokes/random/")
    fun getJoke(): Call<JokeDTO>
}


enum class ErrorType {
    NO_CONNECTION,
    OTHER
}