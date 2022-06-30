package com.example.jokeapp

import androidx.annotation.DrawableRes

class BaseJoke(text: String, punchline: String) : Joke(text, punchline) {
    override fun getIconResId() = R.drawable.ic_baseline_favorite_border_24
}

class FavouriteJoke(text: String, punchline: String) : Joke(text, punchline) {
    override fun getIconResId() = R.drawable.ic_baseline_favorite_24
}

class FailedJoke(text: String) : Joke(text, "") {
    override fun getIconResId() = 0
}

abstract class Joke(private val text: String, private val punchline: String) {

    fun getJokeUI() = "$text\n$punchline"

    @DrawableRes
    abstract fun getIconResId(): Int

    fun map(callBack: DataCallback) = callBack.run {
        provideIconResId(getIconResId())
        provideText(getJokeUI())
    }
}

interface JokeFailure {
    fun getMessage(): String
}

class NoConnection(private val resourseManager: ResourseManager) : JokeFailure {
    override fun getMessage() = resourseManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val resourseManager: ResourseManager) : JokeFailure {
    override fun getMessage() = resourseManager.getString(R.string.service_unavaliable)
}

class NoCacheJokes(private val resourseManager: ResourseManager) : JokeFailure {
    override fun getMessage() = resourseManager.getString(R.string.no_cache_jokes)
}

