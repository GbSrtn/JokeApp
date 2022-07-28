package com.example.jokeapp.data.cache

import com.example.jokeapp.core.data.cache.RealmProvider
import com.example.jokeapp.data.BaseCachedDataSource
import com.example.jokeapp.data.mapper.JokeRealmMapper

class JokeCachedDataSource(
    realmProvider: RealmProvider,
    mapper: JokeRealmMapper,
    commonDataMapper: JokeRealmToCommonDataMapper
) :
    BaseCachedDataSource<JokeRealmModel>(realmProvider, mapper, commonDataMapper) {
    override val dbClass = JokeRealmModel::class.java
}