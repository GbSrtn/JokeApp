package com.example.jokeapp

import com.example.jokeapp.core.Mapper
import com.example.jokeapp.data.JokeRealm

sealed class Joke : Mapper<JokeUiModel> {
    class Success(
        private val text: String,
        private val punchline: String,
        private val favourite: Boolean
    ) : Joke() {
        override fun to(): JokeUiModel {
            return if (favourite) {
                FavouriteJokeUiModel(text, punchline)
            } else {
                BaseJokeUiModel(text, punchline)
            }
        }
    }

    class Failed(private val failure: JokeFailure) : Joke() {
        override fun to(): JokeUiModel {
            return FailedJokeUiModel(failure.getMessage())
        }
    }
}