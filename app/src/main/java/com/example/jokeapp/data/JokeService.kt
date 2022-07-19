package com.example.jokeapp.data

import com.example.jokeapp.core.Mapper
import com.example.jokeapp.data.JokeServerModel
import retrofit2.Call
import retrofit2.http.GET

interface JokeService<T : Mapper<JokeDataModel>> {
    fun getJoke() : Call<T>
}