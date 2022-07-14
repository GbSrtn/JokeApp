package com.example.jokeapp.Test

import com.example.jokeapp.*

//class TestCacheDataSource : CacheDataSource {
//
//    private val list = ArrayList<Pair<Int, Joke>>()
//
//    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
//        if (list.isEmpty()) {
//            jokeCachedCallback.fail()
//        } else {
//            jokeCachedCallback.provide(list.random().second)
//        }
//    }
//
//    override fun addOrRemove(id: Int, joke: Joke): JokeUiModel {
//        val found = list.find {it.first == id}
//        return if (found != null) {
//            val newJoke = found.second.toBaseJoke()
//            list.remove(found)
//            newJoke
//        } else {
//            list.add(Pair(id, joke))
//            joke.toFavouriteJoke()
//        }
//    }
//}