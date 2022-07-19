package com.example.jokeapp.data

import com.example.jokeapp.core.Mapper
import com.example.jokeapp.data.JokeDataModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealm : RealmObject(), Mapper<JokeDataModel> {
    @PrimaryKey
    var id: Int = -1
    var text: String = ""
    var punchline: String = ""

    override fun to() = JokeDataModel(id, text, punchline, true)
}