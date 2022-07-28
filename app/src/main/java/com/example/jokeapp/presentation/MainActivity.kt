package com.example.jokeapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jokeapp.data.*
import com.example.jokeapp.data.cache.*
import com.example.jokeapp.data.mapper.JokeRealmMapper
import com.example.jokeapp.data.mapper.QuoteRealmMapper
import com.example.jokeapp.data.net.*
import com.example.jokeapp.domain.BaseInteractor
import com.example.jokeapp.domain.FailureFactory
import com.example.jokeapp.presentation.BaseCommunication
import com.example.jokeapp.presentation.BaseResourseManager
import com.example.jokeapp.presentation.BaseViewModel
import com.example.jokeapp.presentation.FavouriteDataView
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var jokeViewModel: BaseViewModel
    private lateinit var quoteViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jokeViewModel = (application as JokeApp).jokeViewModel
        val favouriteDataView = findViewById<FavouriteDataView>(R.id.jokeFavouriteDataView)
        favouriteDataView.linkWith(jokeViewModel)
        jokeViewModel.observe(
            this, { state ->
                favouriteDataView.show(state)
            })

        quoteViewModel = (application as JokeApp).quoteViewModel
        val quoteFavouriteDataView = findViewById<FavouriteDataView>(R.id.quoteFavouriteDataView)
        quoteFavouriteDataView.linkWith(quoteViewModel)
        quoteViewModel.observe(
            this, {state ->
                quoteFavouriteDataView.show(state)
            })
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivityUniqueTag", "onPause: ")
    }

    override fun onResume() {
        Log.d("MainActivityUniqueTag", "onResume: ")
        super.onResume()
    }

    override fun onStart() {
        Log.d("MainActivityUniqueTag", "onStart: ")
        super.onStart()
    }
}

class JokeApp : Application() {

    lateinit var jokeViewModel: BaseViewModel
    lateinit var quoteViewModel: BaseViewModel

    override fun onCreate() {
        super.onCreate()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Realm.init(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.ru/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val resourseManager = BaseResourseManager(this)
        val realmProvider = BaseRealmProvider()
        val failureHandler = FailureFactory(resourseManager)

        jokeViewModel = BaseViewModel(
            BaseInteractor(
                BaseRepository(
                    JokeCachedDataSource(realmProvider, JokeRealmMapper(), JokeRealmToCommonDataMapper()),
                    JokeCloudDataSource(retrofit.create(JokeService::class.java)),
                    BaseCachedData<Int>()
                ), failureHandler, CommonSuccessMapper<Int>()
            ), BaseCommunication()
        )

        quoteViewModel = BaseViewModel(
            BaseInteractor(
                BaseRepository(
                    QuoteCachedDataSource(realmProvider, QuoteRealmMapper(), QuoteRealmToCommonDataMapper()),
                    QuoteCloudDataSource(retrofit.create(QuoteService::class.java)),
                    BaseCachedData<String>()
                ), failureHandler, CommonSuccessMapper<String>()
            ), BaseCommunication()
        )
    }
}

