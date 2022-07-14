package com.example.jokeapp

interface CacheDataSource : JokeDataFetcher<Joke, Unit>, ChangeJokeStatus{
}