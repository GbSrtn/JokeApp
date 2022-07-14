package com.example.jokeapp

class Joke(
    private val id: Int,
    private val text: String,
    private val punchline: String
) : ChangeJoke{
    override suspend fun change(changeJokeStatus: ChangeJokeStatus) =
        changeJokeStatus.addOrRemove(id, this)

    fun toBaseJoke() = BaseJokeUiModel(text,punchline)

    fun toFavouriteJoke() = FavouriteJokeUiModel(text,punchline)

    fun toJokeRealm() : JokeRealm {
        return JokeRealm().also {
            it.id = id
            it.text = text
            it.punchline = punchline
        }
    }
}