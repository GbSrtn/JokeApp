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
    fun toJoke() = Joke(id, text, punchline)
}