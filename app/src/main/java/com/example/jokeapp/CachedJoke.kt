package com.example.jokeapp

interface CachedJoke : ChangeJoke{
    fun saveJoke(joke: Joke)
    fun clear()
}