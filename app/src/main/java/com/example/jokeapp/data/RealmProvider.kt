package com.example.jokeapp.data

import io.realm.Realm

interface RealmProvider {
    fun provide(): Realm
}