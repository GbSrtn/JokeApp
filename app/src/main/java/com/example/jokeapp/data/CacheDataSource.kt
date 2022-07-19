package com.example.jokeapp.data

import com.example.jokeapp.JokeDataFetcher

interface CacheDataSource : JokeDataFetcher, ChangeJokeStatus {
}