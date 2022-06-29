package com.example.jokeapp.Test

import com.example.jokeapp.CacheDataSource
import com.example.jokeapp.Joke
import com.example.jokeapp.JokeServerModel

class TestCacheDataSource : CacheDataSource {

    private val map = HashMap<String, JokeServerModel>()

    override fun addOrRemove(id: String, jokeServerModel: JokeServerModel): Joke {
        return if (map.containsKey(id)) {
            val joke = map[id]!!.toBaseJoke()
            map.remove(id)
            joke
        } else {
            map[id] = jokeServerModel
            jokeServerModel.toFavouriteJoke()
        }
    }
}