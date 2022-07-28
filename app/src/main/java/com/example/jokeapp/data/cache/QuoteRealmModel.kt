package com.example.jokeapp.data.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class QuoteRealmModel : RealmObject() {
    @PrimaryKey
    var id : Int = -1
    var firstText : String = ""
    var secondText : String = ""
}
