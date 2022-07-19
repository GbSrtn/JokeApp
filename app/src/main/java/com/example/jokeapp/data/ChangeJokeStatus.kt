package com.example.jokeapp.data

import com.example.jokeapp.data.JokeDataModel

interface ChangeJokeStatus {
    suspend fun addOrRemove(id: Int, joke: JokeDataModel) : JokeDataModel
}