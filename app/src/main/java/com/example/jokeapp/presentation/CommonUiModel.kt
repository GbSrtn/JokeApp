package com.example.jokeapp

import androidx.annotation.DrawableRes
import com.example.jokeapp.core.presentation.Communication
import com.example.jokeapp.presentation.BaseViewModel
import com.example.jokeapp.presentation.State

abstract class CommonUiModel(private val firstText: String, private val secondText: String) {
    protected open fun text() = "$firstText\n$secondText"
    @DrawableRes
    abstract fun getIconResId(): Int
    open fun show(communication: Communication) = communication.showState(
        State.Initial(text(), getIconResId()))
}

class BaseCommonUiModel(text: String, punchline: String) : CommonUiModel(text, punchline) {
    override fun getIconResId() = R.drawable.ic_baseline_favorite_border_24
}

class FavouriteCommonUiModel(text: String, punchline: String) : CommonUiModel(text, punchline) {
    override fun getIconResId() = R.drawable.ic_baseline_favorite_24
}

class FailedCommonUiModel(private val text: String) : CommonUiModel(text, "") {
    override fun text() = text
    override fun getIconResId() = 0
    override fun show(communication: Communication) = communication.showState(
        State.Failed(text(), getIconResId())
    )
}

