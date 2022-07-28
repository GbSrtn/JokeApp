package com.example.jokeapp.data.cache

import com.example.jokeapp.core.data.cache.RealmProvider
import com.example.jokeapp.data.BaseCachedDataSource
import com.example.jokeapp.data.mapper.QuoteRealmMapper
import io.realm.Realm

class QuoteCachedDataSource(
    realmProvider: RealmProvider,
    mapper: QuoteRealmMapper,
    commonDataMapper: QuoteRealmToCommonDataMapper,
) :
    BaseCachedDataSource<QuoteRealmModel, String>(realmProvider, mapper,commonDataMapper) {
    override val dbClass = QuoteRealmModel::class.java
    override fun findRealmObject(realm: Realm, id: String) =
        realm.where(dbClass).equalTo("id", id).findFirst()
}