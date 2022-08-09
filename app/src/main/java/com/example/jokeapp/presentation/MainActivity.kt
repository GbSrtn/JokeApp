package com.example.jokeapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapp.data.*
import com.example.jokeapp.data.cache.*
import com.example.jokeapp.data.mapper.JokeRealmMapper
import com.example.jokeapp.data.mapper.QuoteRealmMapper
import com.example.jokeapp.data.net.*
import com.example.jokeapp.domain.BaseInteractor
import com.example.jokeapp.domain.FailureFactory
import com.example.jokeapp.presentation.*
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var jokeViewModel: BaseViewModel<Int>
    private lateinit var adapter: CommonDataRecyclerAdapter<Int>
    //private lateinit var quoteViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jokeViewModel = (application as JokeApp).jokeViewModel
        val jokeCommunication =(application as JokeApp).jokeCommunication
        val favouriteDataView = findViewById<FavouriteDataView>(R.id.jokeFavouriteDataView)

        favouriteDataView.linkWith(jokeViewModel)
        jokeViewModel.observe(
            this, { state ->
                favouriteDataView.show(state)
            })


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val observer: (t: List<CommonUiModel<Int>>) -> Unit = { list ->
            adapter.update()
        }

        adapter = CommonDataRecyclerAdapter(object :
            CommonDataRecyclerAdapter.FavouriteItemClickListener<Int> {
            override fun change(id: Int) {
                Snackbar.make(
                    favouriteDataView,
                    R.string.remove_from_favourites,
                    Snackbar.LENGTH_SHORT
                ).setAction(R.string.yes) {
                    val position = jokeViewModel.changeItemStatus(id)
                    adapter.update(Pair(false, position))
                }.show()
            }
        }, jokeCommunication)

        recyclerView.adapter = adapter

        jokeViewModel.observeList(this, observer)
        jokeViewModel.getItemList()

//        quoteViewModel = (application as JokeApp).quoteViewModel
//        val quoteFavouriteDataView = findViewById<FavouriteDataView>(R.id.quoteFavouriteDataView)
//        quoteFavouriteDataView.linkWith(quoteViewModel)
//        quoteViewModel.observe(
//            this, {state ->
//                quoteFavouriteDataView.show(state)
//            })
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

    lateinit var jokeViewModel: BaseViewModel<Int>
    lateinit var jokeCommunication: BaseCommunication<Int>
    lateinit var quoteViewModel: BaseViewModel<String>


    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.ru/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val realmProvider = BaseRealmProvider()
        val cachedDataSource = JokeCachedDataSource(realmProvider, JokeRealmMapper(), JokeRealmToCommonDataMapper())
        val cloudDataSource = JokeCloudDataSource(retrofit.create(JokeService::class.java))
        val jokeRepository = BaseRepository(cachedDataSource,cloudDataSource,BaseCachedData())
        val failureHandler = FailureFactory(BaseResourseManager(this))
        val mapper = CommonSuccessMapper<Int>()
        val interactor = BaseInteractor(jokeRepository, failureHandler, mapper)

        jokeCommunication = BaseCommunication()
        jokeViewModel =  BaseViewModel(interactor, jokeCommunication)



//        quoteViewModel = BaseViewModel(
//            BaseInteractor(
//                BaseRepository(
//                    QuoteCachedDataSource(realmProvider, QuoteRealmMapper(), QuoteRealmToCommonDataMapper()),
//                    QuoteCloudDataSource(retrofit.create(QuoteService::class.java)),
//                    BaseCachedData<String>()
//                ), failureHandler, CommonSuccessMapper<String>()
//            ), BaseCommunication()
//        )
    }
}

