package com.example.jokeapp.Base

import com.example.jokeapp.*

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val resourseManager: ResourseManager
) : Model {
    private val noConnection by lazy { NoConnection(resourseManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourseManager) }
    private val noCachedJoke by lazy { NoCacheJokes(resourseManager) }

    private var jokeCallback: JokeCallback? = null
    private var cachedJoke: Joke? = null
    private var getJokeFromCache = false

    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }

    override fun getJoke() {
        if (getJokeFromCache) {
            cacheDataSource.getJoke(object: JokeCachedCallback{
                override fun provide(joke: Joke) {
                    cachedJoke = joke
                    jokeCallback?.provide(joke.toFavouriteJoke())
                }

                override fun fail() {
                    cachedJoke = null
                    jokeCallback?.provide(FailedJokeUiModel(noCachedJoke.getMessage()))
                }
            })
        } else {
            cloudDataSource.getJoke(object: JokeCloudCallback{
                override fun provide(joke: Joke) {
                    cachedJoke = joke
                    jokeCallback?.provide(joke.toBaseJoke())
                }

                override fun fail(error: ErrorType) {
                    cachedJoke = null
                    val failure = if (error == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
                    jokeCallback?.provide(FailedJokeUiModel(failure.getMessage()))
                }

            })
        }
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {
        cachedJoke?.let {
            jokeCallback.provide(it.change(cacheDataSource))
        }
    }

    override fun init(callback: JokeCallback) {
        this.jokeCallback = callback
    }

    override fun clear() {
        jokeCallback = null
    }
}