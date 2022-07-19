package com.example.jokeapp

interface JokeInteractor {
    suspend fun getJoke() : Joke

    suspend fun  changeFavourites() : Joke

    fun  getFavouriteJokes(favorites: Boolean)
}