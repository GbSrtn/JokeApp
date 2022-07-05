package com.example.jokeapp

import io.realm.Realm

interface RealmProvider {
    fun provide(): Realm
}