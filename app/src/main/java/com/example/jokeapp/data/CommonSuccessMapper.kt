package com.example.jokeapp.data

import com.example.jokeapp.domain.CommonItem
import com.example.jokeapp.core.data.CommonDataModelMapper

class CommonSuccessMapper<E> : CommonDataModelMapper<CommonItem.Success, E> {
    override fun map(id: E, firstText: String, secondText: String, cached: Boolean) =
        CommonItem.Success(firstText, secondText, cached)
    }
