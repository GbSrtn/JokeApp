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

    private var cacheJokeServerModel: JokeServerModel? = null

    override fun changeJokeStatus(jokeCallback: JokeCallback) {
        cacheJokeServerModel?.change(cacheDataSource)?.let {
            jokeCallback.provide(it)
        }
    }

    private var getJokeFromCache = false

    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }

    override fun getJoke() {
        if (getJokeFromCache) {
            cacheDataSource.getJoke(object: JokeCachedCallback{
                override fun provide(jokeServerModel: JokeServerModel) {
                    cacheJokeServerModel = jokeServerModel
                    jokeCallback?.provide(jokeServerModel.toFavouriteJoke())
                }
                override fun fail() {
                    jokeCallback?.provide(FailedJoke(noCachedJoke.getMessage()))
                }
            })
        } else {
            cloudDataSource.getJoke(object: JokeCloudCallback{
                override fun provide(joke: JokeServerModel) {
                    cacheJokeServerModel = joke
                    jokeCallback?.provide(joke.toBaseJoke())
                }
                override fun fail(error: ErrorType) {
                    cacheJokeServerModel = null
                    val failure = if (error == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
                    jokeCallback?.provide(FailedJoke(failure.getMessage()))
                }

            })
        }
    }

    override fun init(callback: JokeCallback) {
        this.jokeCallback = callback
    }

    override fun clear() {
        jokeCallback = null
    }


}