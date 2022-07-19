package com.example.jokeapp.data

import com.example.jokeapp.Joke
import com.example.jokeapp.JokeFailureHandler
import com.example.jokeapp.JokeInteractor
import java.lang.Exception

class BaseJokeInteractor(
    private val repository: JokeRepository,
    private val jokeFailureHandler: JokeFailureHandler,
    private val mapper: JokeDataModelMapper<Joke.Success>
) : JokeInteractor {
    override suspend fun getJoke(): Joke {
        return try {
            repository.getJoke().map(mapper)
        } catch (e: Exception) {
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }

    override suspend fun changeFavourites(): Joke {
        return try {
            repository.changeJokeStatus().map(mapper)
        } catch (e: Exception) {
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }

    override fun getFavouriteJokes(favorites: Boolean) {
        repository.chooseDataSource(favorites)
    }
}