package com.example.jokeapp

class TestCloudDataSource : CloudDataSource {
    override fun getJoke(callback: JokeCloudCallback) {
        callback.provide(JokeServerModel("A", "ШУТКАААААААААА"))
    }
}