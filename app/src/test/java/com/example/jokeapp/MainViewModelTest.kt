package com.example.jokeapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.jetbrains.annotations.TestOnly
import org.junit.Test
import org.junit.Assert.*

//class MainViewModelTest {
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun test_get_joke_from_cloud_success() : Unit = runBlocking {
//        val model = TestModel()
//        val communication = TestCommunication()
//        val viewModel = MainViewModel(model, communication, TestCoroutineDispatcher())
//
//        model.success = true
//        viewModel.chooseDataSource(false)
//        viewModel.getJoke()
//
//        val actualText = communication.text
//        val actualId = communication.id
//        val expectedText = "cloudJokeText\ncloudJokePunchline"
//        assertEquals(expectedText,actualText)
//        assertNotEquals(0, actualId)
//    }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun test_get_joke_from_cloud_fail() : Unit = runBlocking {
//        val model = TestModel()
//        val communication = TestCommunication()
//        val viewModel = MainViewModel(model, communication, TestCoroutineDispatcher())
//
//        model.success = false
//        viewModel.chooseDataSource(false)
//        viewModel.getJoke()
//
//        val actualText = communication.text
//        val actualId = communication.id
//        val expectedText = "no connection"
//        val expectedId = 0
//        assertEquals(expectedText,actualText)
//        assertEquals(expectedId, actualId)
//    }
//
//    @org.junit.Test
//    fun changeJokeStatus() {
//    }
//
//    @org.junit.Test
//    fun chooseDataSource() {
//    }
//
//    @org.junit.Test
//    fun getJoke() {
//    }
//
//    @org.junit.Test
//    fun observe() {
//    }
//
//    private inner class TestModel: Model {
//
//        private val cacheJokeUiModel = BaseJokeUiModel("cachedJokeText", "cachedJokePunchline")
//        private val cacheJokeFailure = FailedJokeUiModel("cacheFailed")
//        private val cloudJokeUIModel = BaseJokeUiModel("cloudJokeText", "cloudJokePunchline")
//        private val cloudJokeFailure = FailedJokeUiModel("no connection")
//
//        var success: Boolean = false
//
//        private var getFromCache = false
//        private var cachedJoke: JokeUiModel? = null
//
//        override suspend fun getJoke(): JokeUiModel {
//            return if (success) {
//                if (getFromCache) {
//                    cacheJokeUiModel.also {
//                        cachedJoke = it
//                    }
//                } else {
//                    cloudJokeUIModel.also {
//                        cachedJoke = it
//                    }
//                }
//            } else {
//                cachedJoke = null
//                if (getFromCache) {
//                    cacheJokeFailure
//                } else {
//                    cloudJokeFailure
//                }
//            }
//        }
//
//        override suspend fun changeJokeStatus(): JokeUiModel? {
//            TODO("Not yet implemented")
//        }
//
//        override fun chooseDataSource(cached: Boolean) {
//            getFromCache = cached
//        }
//
//    }
//
//    private inner class TestCommunication : Communication {
//        var text = ""
//        var id = -1
//        var observedCount = 0
//
//        override fun showData(data: Pair<String, Int>) {
//            text = data.first
//            id = data.second
//        }
//
//        override fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>) {
//            observedCount++
//        }
//    }
//
//}