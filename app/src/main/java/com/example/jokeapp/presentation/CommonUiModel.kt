package com.example.jokeapp

import androidx.annotation.DrawableRes
import com.example.jokeapp.core.presentation.Communication
import com.example.jokeapp.core.presentation.customViews.ShowText
import com.example.jokeapp.presentation.BaseViewModel
import com.example.jokeapp.presentation.CommonDataRecyclerAdapter
import com.example.jokeapp.presentation.State

abstract class CommonUiModel<T>(private val firstText: String, private val secondText: String) {
    protected open fun text() = "$firstText\n$secondText"
    open fun change(listener: CommonDataRecyclerAdapter.FavouriteItemClickListener<T>) = Unit
    open fun matches(id: T) : Boolean = false
    @DrawableRes
    abstract fun getIconResId(): Int
    open fun show(communication: Communication) = communication.showState(
        State.Initial(text(), getIconResId())
    )
    fun show(showText: ShowText) = showText.show(text())
}

class BaseCommonUiModel<E>(text: String, punchline: String) : CommonUiModel<E>(text, punchline) {
    override fun getIconResId() = R.drawable.ic_baseline_favorite_border_24
}

class FavouriteCommonUiModel<E>(private val id: E, text: String, punchline: String) :
    CommonUiModel<E>(text, punchline) {
    override fun getIconResId() = R.drawable.ic_baseline_favorite_24

    override fun change(listener: CommonDataRecyclerAdapter.FavouriteItemClickListener<E>) =
        listener.change(id)

    override fun matches(id: E): Boolean = this.id == id
}

class FailedCommonUiModel<E>(private val text: String) : CommonUiModel<E>(text, "") {
    override fun text() = text
    override fun getIconResId() = 0
    override fun show(communication: Communication) = communication.showState(
        State.Failed(text(), getIconResId())
    )
}

