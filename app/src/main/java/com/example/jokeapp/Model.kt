package com.example.jokeapp

interface Model {
    suspend fun changeJokeStatus(): JokeUiModel?
    fun chooseDataSource(cached : Boolean)
    suspend fun getJoke(): JokeUiModel
}