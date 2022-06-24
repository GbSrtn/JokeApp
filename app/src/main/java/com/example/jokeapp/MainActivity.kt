package com.example.jokeapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).viewModel
        val button = findViewById<Button>(R.id.actionButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val textView = findViewById<TextView>(R.id.textView)
        progressBar.visibility = View.INVISIBLE

        button.setOnClickListener{
            button.isEnabled = false
            progressBar.visibility = View.VISIBLE
            viewModel.getJoke()
        }

        viewModel.init(object : TextCallBack {
            override fun provideText(text: String) = runOnUiThread{
                button.isEnabled =true
                progressBar.visibility = View.INVISIBLE
                textView.text = text
            }
            
        })
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}

class JokeApp : Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        viewModel = ViewModel(BaseModel(retrofit.create(JokeService::class.java), BaseResourseManager(this)))
    }
}

