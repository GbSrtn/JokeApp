package com.example.jokeapp

import io.realm.Realm

class BaseRealmProvider : RealmProvider{
    override fun provide(): Realm = Realm.getDefaultInstance()
}