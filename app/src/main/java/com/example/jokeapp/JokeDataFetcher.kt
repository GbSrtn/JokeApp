package com.example.jokeapp

import com.example.jokeapp.data.JokeDataModel

interface JokeDataFetcher {
    suspend fun getJoke(): JokeDataModel
}