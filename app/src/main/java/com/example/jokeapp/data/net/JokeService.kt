package com.example.jokeapp.data.net

import com.example.jokeapp.data.net.JokeServerModel
import retrofit2.Call
import retrofit2.http.GET

interface JokeService {
    @GET("https://v2.jokeapi.dev/joke/Any?type=twopart")
    fun getJoke(): Call<JokeServerModel>
}