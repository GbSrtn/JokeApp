package com.example.jokeapp.data

import com.example.jokeapp.ChangeJoke
import com.example.jokeapp.Joke

interface CachedJoke : ChangeJoke {
    fun saveJoke(joke: JokeDataModel)
    fun clear()
}