package com.example.jokeapp.data.cache

import com.example.jokeapp.data.CommonDataModel
import com.example.jokeapp.data.RealmToCommonDataMapper

class JokeRealmToCommonDataMapper : RealmToCommonDataMapper<JokeRealmModel, Int> {
    override fun map(realmObject: JokeRealmModel) =
        CommonDataModel(realmObject.id,realmObject.text,realmObject.punchline, true)
}