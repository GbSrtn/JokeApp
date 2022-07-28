package com.example.jokeapp.domain

import com.example.jokeapp.BaseCommonUiModel
import com.example.jokeapp.CommonUiModel
import com.example.jokeapp.FailedCommonUiModel
import com.example.jokeapp.FavouriteCommonUiModel
import com.example.jokeapp.core.Mapper
import com.example.jokeapp.core.presentation.Failure

sealed class CommonItem : Mapper<CommonUiModel> {
    class Success(
        private val firstText: String,
        private val secondText: String,
        private val favourite: Boolean
    ) : CommonItem() {
        override fun to(): CommonUiModel {
            return if (favourite) {
                FavouriteCommonUiModel(firstText, secondText)
            } else {
                BaseCommonUiModel(firstText, secondText)
            }
        }
    }

    class Failed(private val failure: Failure) : CommonItem() {
        override fun to(): CommonUiModel {
            return FailedCommonUiModel(failure.getMessage())
        }
    }
}