package com.example.jokeapp

class CacheResultHandler(
    private val cachedJoke: CachedJoke,
    jokeDataFetcher: JokeDataFetcher<Joke, Unit>,
    private val noCachedJokes: JokeFailure
) : BaseResultHandler<Joke, Unit>(jokeDataFetcher) {

    override fun handleResult(result: Result<Joke, Unit>) = when (result) {
        is Result.Success -> result.data.let {
            cachedJoke.saveJoke(it)
            it.toFavouriteJoke()
        }
        else -> {
            cachedJoke.clear()
            FailedJokeUiModel(noCachedJokes.getMessage())
        }

    }
}