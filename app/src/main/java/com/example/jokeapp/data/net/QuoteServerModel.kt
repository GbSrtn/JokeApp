package com.example.jokeapp.data.net

import com.example.jokeapp.core.Mapper
import com.example.jokeapp.data.CommonDataModel
import com.google.gson.annotations.SerializedName

class QuoteServerModel (
    @SerializedName("_id")
    private val id : String,
    @SerializedName("content")
    private val firstText: String,
    @SerializedName("author")
    private val secondText: String
) : Mapper<CommonDataModel<String>> {
    override fun to() = CommonDataModel(id, firstText, secondText)

}