package com.example.jokeapp.data.mapper

import com.example.jokeapp.core.data.CommonDataModelMapper
import com.example.jokeapp.data.cache.JokeRealmModel

class JokeRealmMapper : CommonDataModelMapper<JokeRealmModel> {
    override fun map(id: Int, firstText: String, secondText: String, cached: Boolean): JokeRealmModel {
        return JokeRealmModel().also { joke ->
            joke.id = id
            joke.text = firstText
            joke.punchline = secondText
        }
    }
}