package com.example.jokeapp.Test

import com.example.jokeapp.CacheDataSource
import com.example.jokeapp.Joke
import com.example.jokeapp.JokeCachedCallback
import com.example.jokeapp.JokeServerModel

class TestCacheDataSource : CacheDataSource {

    private val list = ArrayList<Pair<Int, JokeServerModel>>()

    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
        if (list.isEmpty()) {
            jokeCachedCallback.fail()
        } else {
            jokeCachedCallback.provide(list.random().second)
        }
    }

    override fun addOrRemove(id: Int, jokeServerModel: JokeServerModel): Joke {
        val found = list.find {it.first == id}
        return if (found != null) {
            val joke = found.second.toBaseJoke()
            list.remove(found)
            joke
        } else {
            list.add(Pair(id, jokeServerModel))
            jokeServerModel.toFavouriteJoke()
        }
    }
}