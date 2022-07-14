package com.example.jokeapp

interface ChangeJokeStatus {
    suspend fun addOrRemove(id: Int, joke: Joke) : JokeUiModel
}