package com.example.jokeapp

interface ChangeJoke {
    suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeUiModel?
}