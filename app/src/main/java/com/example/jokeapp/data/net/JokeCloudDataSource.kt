package com.example.jokeapp.data.net

class JokeCloudDataSource(private val service: JokeService) :
    BaseCloudDataSource<JokeServerModel>(){
    override fun getServerModel() = service.getJoke()
}