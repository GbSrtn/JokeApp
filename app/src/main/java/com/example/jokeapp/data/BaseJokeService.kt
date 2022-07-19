package com.example.jokeapp.data

import retrofit2.Call
import retrofit2.http.GET

interface BaseJokeService {
    @GET("https://v2.jokeapi.dev/joke/Any?type=twopart")
    fun getJoke(): Call<JokeServerModel>
}