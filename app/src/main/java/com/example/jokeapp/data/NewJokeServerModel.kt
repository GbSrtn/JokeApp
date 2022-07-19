package com.example.jokeapp.data

import com.example.jokeapp.core.Mapper
import com.google.gson.annotations.SerializedName

class NewJokeServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("joke")
    private val text: String
) : Mapper<JokeDataModel> {
    override fun to() = JokeDataModel(id, text, "")
}