package com.example.jokeapp

import androidx.annotation.DrawableRes

class BaseJokeUiModel(text: String, punchline: String) : JokeUiModel(text, punchline) {
    override fun getIconResId() = R.drawable.ic_baseline_favorite_border_24
}

class FavouriteJokeUiModel(text: String, punchline: String) : JokeUiModel(text, punchline) {
    override fun getIconResId() = R.drawable.ic_baseline_favorite_24
}

class FailedJokeUiModel(private val text: String) : JokeUiModel(text, "") {
    override fun text() = text
    override fun getIconResId() = 0
    override fun show(communication: Communication) = communication.showState(
        BaseViewModel.State.Failed(text(), getIconResId())
    )
}

abstract class JokeUiModel(private val text: String, private val punchline: String) {
    protected open fun text() = "$text\n$punchline"
    @DrawableRes
    abstract fun getIconResId(): Int
    open fun show(communication: Communication) = communication.showState(
        BaseViewModel.State.Initial(text(), getIconResId()))
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

class NoCachedJokes(private val resourseManager: ResourseManager) : JokeFailure {
    override fun getMessage() = resourseManager.getString(R.string.no_cache_jokes)
}

class GenericError(private val resourseManager: ResourseManager) : JokeFailure {
    override fun getMessage() = resourseManager.getString(R.string.generic_fail_message)
}

