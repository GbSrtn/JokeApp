package com.example.jokeapp

class Joke(private val text: String, private val punchline: String) {

    fun getJokeUI() = "$text\n$punchline"
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

