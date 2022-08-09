package com.example.jokeapp.domain

import com.example.jokeapp.BaseCommonUiModel
import com.example.jokeapp.CommonUiModel
import com.example.jokeapp.FailedCommonUiModel
import com.example.jokeapp.FavouriteCommonUiModel
import com.example.jokeapp.core.Mapper
import com.example.jokeapp.core.presentation.Failure

sealed class CommonItem<E> : Mapper<CommonUiModel<E>> {
    class Success<E>(
        private val id: E,
        private val firstText: String,
        private val secondText: String,
        private val favourite: Boolean
    ) : CommonItem<E>() {
        override fun to() = if (favourite) {
            FavouriteCommonUiModel(id, firstText, secondText)
        } else {
            BaseCommonUiModel(firstText, secondText)
        }
    }

    class Failed<E>(private val failure: Failure) : CommonItem<E>() {
        override fun to(): CommonUiModel<E> {
            return FailedCommonUiModel(failure.getMessage())
        }
    }
}