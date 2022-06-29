package com.example.jokeapp

import com.google.gson.annotations.SerializedName

data class JokeServerModel(
    @SerializedName("id")
    private val id: String,
    @SerializedName("value")
    private val text: String
) {
    fun toJoke() = BaseJoke(text)

    fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(id, this)

    fun toBaseJoke() = BaseJoke(text)

    fun toFavouriteJoke() = FavouriteJoke(text)
}