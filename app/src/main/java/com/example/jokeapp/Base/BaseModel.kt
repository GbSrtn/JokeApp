package com.example.jokeapp.Base

import com.example.jokeapp.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val resourseManager: ResourseManager
) : Model {
    private val noConnection by lazy { NoConnection(resourseManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourseManager) }
    private val noCachedJoke by lazy { NoCacheJokes(resourseManager) }

    private var cachedJoke: Joke? = null
    private var getJokeFromCache = false

    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }

    override suspend fun getJoke(): JokeUiModel = withContext(Dispatchers.IO) {
        if (getJokeFromCache) {
            return@withContext when (val result = cacheDataSource.getJoke()) {
                is Result.Success<Joke> -> result.data.let {
                    cachedJoke = it
                    it.toFavouriteJoke()
                }
//                if Result.Error -> {
//                    cachedJoke = null
//                    FailedJokeUiModel(noCachedJoke.getMessage())
//                }
                else -> {
                    cachedJoke = null
                    FailedJokeUiModel(noCachedJoke.getMessage())
                }
            }
        } else {
            return@withContext when (val result = cloudDataSource.getJoke()) {
                is Result.Success<JokeServerModel> -> {
                    result.data.toJoke().let {
                        cachedJoke = it
                        it.toBaseJoke()
                    }
                }
                is Result.Error<ErrorType> -> {
                    cachedJoke = null
                    val failure = if (result.exception == ErrorType.NO_CONNECTION)
                        noConnection
                    else serviceUnavailable
                    FailedJokeUiModel(failure.getMessage())
                }
            }
        }
    }

    override suspend fun changeJokeStatus(): JokeUiModel? = cachedJoke?.change(cacheDataSource)

}
