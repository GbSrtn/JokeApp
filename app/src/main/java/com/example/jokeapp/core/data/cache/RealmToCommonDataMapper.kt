package com.example.jokeapp.data

import com.example.jokeapp.data.cache.QuoteRealmModel
import io.realm.RealmObject

interface RealmToCommonDataMapper<T :  RealmObject, E> {
    fun map(realmObject: T): CommonDataModel<E>
}
