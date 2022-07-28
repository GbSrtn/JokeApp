package com.example.jokeapp.data

import com.example.jokeapp.domain.CommonItem
import com.example.jokeapp.core.data.CommonDataModelMapper

class CommonSuccessMapper : CommonDataModelMapper<CommonItem.Success> {
    override fun map(id: Int, firstText: String, secondText: String, cached: Boolean) =
        CommonItem.Success(firstText, secondText, cached)
    }
