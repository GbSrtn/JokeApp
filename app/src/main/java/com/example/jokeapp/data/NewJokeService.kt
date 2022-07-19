package com.example.jokeapp.data

import retrofit2.Call
import retrofit2.http.GET

interface NewJokeService {
    @GET("https://v2.jokeapi.dev/joke/Any?type=single")
    fun getJoke(): Call<NewJokeServerModel>
}