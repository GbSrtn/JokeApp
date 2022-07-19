package com.example.jokeapp.data

import retrofit2.Call

class JokeCloudDataSource(private val service: BaseJokeService) :
    BaseCloudDataSource<JokeServerModel>(){
    override fun getJokeServerModel() = service.getJoke()
}