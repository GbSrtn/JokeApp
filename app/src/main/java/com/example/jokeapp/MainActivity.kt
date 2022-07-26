package com.example.jokeapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.jokeapp.data.*
import com.example.jokeapp.presentation.BaseCommunication
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).viewModel

//        val progressBar = findViewById<CorrectProgress>(R.id.progressBar)
//        val button = findViewById<CorrectButton>(R.id.actionButton)
//        val textView = findViewById<CorrectTextView>(R.id.textView)
//        val changeButton = findViewById<CorrectImageButton>(R.id.changeButton)
//        val checkBox = findViewById<CheckBox>(R.id.checkBox)
//
//        progressBar.visibility = View.INVISIBLE
//
//        changeButton.setOnClickListener {
//            viewModel.changeJokeStatus()
//        }
//
//        button.setOnClickListener{
//            viewModel.getJoke()
//        }
//
//        checkBox.setOnCheckedChangeListener { _, isChecked ->
//            viewModel.chooseFavourites(isChecked)
//        }

        val favouriteDataView = findViewById<FavouriteDataView>(R.id.jokeFavouriteDataView)
        favouriteDataView.listenChanges { isChecked ->
            viewModel.chooseFavourites(isChecked)
        }

        favouriteDataView.handleActionButton {
            viewModel.getJoke()
        }

        favouriteDataView.handleChangeButton {
            viewModel.changeJokeStatus()
        }

        viewModel.observe(
            this, { state ->
                favouriteDataView.show(state)
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

    lateinit var viewModel: BaseViewModel

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

        val cachedJoke = BaseCachedJoke()
        val resourseManager = BaseResourseManager(this)
        val cacheDataSource = BaseCacheDataSource(BaseRealmProvider(), JokeRealmMapper())
        val cloudDataSource = NewJokeCloudDataSource(retrofit.create(NewJokeService::class.java))
        //val cloudDataSource = JokeCloudDataSource(retrofit.create(BaseJokeService::class.java))
        val repository = BaseJokeRepository(cacheDataSource,cloudDataSource, cachedJoke)
        val interactor = BaseJokeInteractor(repository, JokeFailureFactory(resourseManager), JokeSuccessMapper())
        viewModel = BaseViewModel(interactor, BaseCommunication())
    }
}

