package com.example.jokeapp

import com.google.gson.annotations.SerializedName

data class JokeServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("setup")
    private val text: String,
    @SerializedName("delivery")
    private val punchline: String
) {
    fun toJoke() = BaseJoke(text,punchline)

    fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(id, this)

    fun toBaseJoke() = BaseJoke(text,punchline)

    fun toFavouriteJoke() = FavouriteJoke(text,punchline)

    fun toJokeRealm() : JokeRealm {
        return JokeRealm().also {
            it.id = id
            it.text = text
            it.puchline = punchline
        }
    }
}