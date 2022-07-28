package com.example.jokeapp.data.cache

import com.example.jokeapp.core.data.cache.RealmProvider
import com.example.jokeapp.data.BaseCachedDataSource
import com.example.jokeapp.data.mapper.JokeRealmMapper
import io.realm.Realm

class JokeCachedDataSource(
    realmProvider: RealmProvider,
    mapper: JokeRealmMapper,
    commonDataMapper: JokeRealmToCommonDataMapper
) :
    BaseCachedDataSource<JokeRealmModel, Int>(realmProvider, mapper, commonDataMapper) {
    override val dbClass = JokeRealmModel::class.java
    override fun findRealmObject(realm: Realm, id: Int) =
        realm.where(dbClass).equalTo("id", id).findFirst()

}