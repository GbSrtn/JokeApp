package com.example.jokeapp.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.jokeapp.CommonUiModel

interface ListCommunication<T> {
    fun getList() : List<CommonUiModel<T>>

    fun showDataList(list: List<CommonUiModel<T>>)

    fun observerList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel<T>>>)

    fun removeItem(id: T) : Int
}