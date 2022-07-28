package com.example.jokeapp.data.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class QuoteRealmModel : RealmObject() {
    @PrimaryKey
    var id : String = ""
    var firstText : String = ""
    var secondText : String = ""
}
