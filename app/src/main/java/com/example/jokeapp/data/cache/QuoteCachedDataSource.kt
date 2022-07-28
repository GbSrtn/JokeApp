package com.example.jokeapp.data.cache

import com.example.jokeapp.core.data.cache.RealmProvider
import com.example.jokeapp.data.BaseCachedDataSource
import com.example.jokeapp.data.mapper.QuoteRealmMapper

class QuoteCachedDataSource(
    realmProvider: RealmProvider,
    mapper: QuoteRealmMapper,
    commonDataMapper: QuoteRealmToCommonDataMapper,
) :
    BaseCachedDataSource<QuoteRealmModel>(realmProvider, mapper,commonDataMapper) {
    override val dbClass = QuoteRealmModel::class.java
}