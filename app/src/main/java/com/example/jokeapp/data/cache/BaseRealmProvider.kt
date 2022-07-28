package com.example.jokeapp.data.cache

import com.example.jokeapp.core.data.cache.RealmProvider
import io.realm.Realm

class BaseRealmProvider : RealmProvider {
    override fun provide(): Realm = Realm.getDefaultInstance()
}