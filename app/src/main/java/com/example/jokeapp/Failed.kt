package com.example.jokeapp

class Failed(private val failure: JokeFailure) : Joke() {
    override fun to(): JokeUiModel {
        return FailedJokeUiModel(failure.getMessage())
    }
}