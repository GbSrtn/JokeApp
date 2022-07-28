package com.example.jokeapp.presentation

import androidx.annotation.StringRes
import com.example.jokeapp.R
import com.example.jokeapp.ResourseManager
import com.example.jokeapp.core.presentation.Failure

abstract class BaseFailure(private val resourseManager: ResourseManager) : Failure {
    @StringRes
    protected abstract fun getMessageResId(): Int

    override fun getMessage(): String = resourseManager.getString(getMessageResId())
}

class NoConnection(resourseManager: ResourseManager) : BaseFailure(resourseManager) {
    override fun getMessageResId() = R.string.no_connection
}

class ServiceUnavailable(resourseManager: ResourseManager) : BaseFailure(resourseManager) {
    override fun getMessageResId() = R.string.service_unavaliable
}

class NoCachedData(resourseManager: ResourseManager) : BaseFailure(resourseManager) {
    override fun getMessageResId() = R.string.no_cache_data
}

class GenericError(resourseManager: ResourseManager) : BaseFailure(resourseManager) {
    override fun getMessageResId() = R.string.generic_fail_message
}