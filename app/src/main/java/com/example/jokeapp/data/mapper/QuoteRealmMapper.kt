package com.example.jokeapp.data.mapper

import com.example.jokeapp.core.data.CommonDataModelMapper
import com.example.jokeapp.data.cache.QuoteRealmModel

class QuoteRealmMapper : CommonDataModelMapper<QuoteRealmModel> {
    override fun map(
        id: Int,
        firstText: String,
        secondText: String,
        cached: Boolean
    ): QuoteRealmModel {
        return QuoteRealmModel().also { quote ->
            quote.id = id
            quote.firstText = firstText
            quote.secondText = secondText
        }
    }
}