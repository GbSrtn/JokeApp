package com.example.jokeapp.data.net

import com.example.jokeapp.core.Mapper
import com.example.jokeapp.data.CommonDataModel
import com.google.gson.annotations.SerializedName

data class JokeServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("setup")
    private val text: String,
    @SerializedName("delivery")
    private val punchline: String) : Mapper<CommonDataModel<Int>> {

    override fun to() = CommonDataModel(id,text, punchline)
}