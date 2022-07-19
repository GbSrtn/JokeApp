package com.example.jokeapp.data

interface JokeRepository {
    suspend fun changeJokeStatus(): JokeDataModel
    suspend fun getJoke(): JokeDataModel
    fun chooseDataSource(cached : Boolean)
}