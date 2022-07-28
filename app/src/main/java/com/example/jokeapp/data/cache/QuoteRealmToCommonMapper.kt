package com.example.jokeapp.data.cache

import com.example.jokeapp.data.CommonDataModel
import com.example.jokeapp.data.RealmToCommonDataMapper

class QuoteRealmToCommonDataMapper : RealmToCommonDataMapper<QuoteRealmModel> {
    override fun map(realmObject: QuoteRealmModel) =
        CommonDataModel(realmObject.id, realmObject.firstText, realmObject.secondText, true)
}