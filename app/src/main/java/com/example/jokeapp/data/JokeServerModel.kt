package com.example.jokeapp.data

import com.example.jokeapp.Joke
import com.example.jokeapp.core.Mapper
import com.google.gson.annotations.SerializedName

data class JokeServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("setup")
    private val text: String,
    @SerializedName("delivery")
    private val punchline: String) : Mapper<JokeDataModel> {

    override fun to() = JokeDataModel(id,text, punchline)
}