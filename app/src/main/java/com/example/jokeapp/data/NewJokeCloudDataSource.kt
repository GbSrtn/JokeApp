package com.example.jokeapp.data

import retrofit2.Call

class NewJokeCloudDataSource(private val service: NewJokeService) :
    BaseCloudDataSource<NewJokeServerModel>() {
    override fun getJokeServerModel() = service.getJoke()
}