package com.example.jokeapp

import com.google.gson.annotations.SerializedName

data class JokeDTO(
    @SerializedName("id")
    private val id: String,
    @SerializedName("value")
    private val text: String
) {
    fun toJoke() = BaseJoke(text)
}