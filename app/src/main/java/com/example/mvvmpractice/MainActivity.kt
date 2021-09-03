package com.example.mvvmpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.example.mvvmpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MyViewModel
    private val myAdapter = MyAdapter()
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitService = ApiInterface.getInstance()
        val myrepository = Repo(retrofitService)
        binding.recyclerView.adapter = myAdapter

        viewModel = ViewModelProvider(this, MyViewModelFactory(myrepository))
            .get(MyViewModel::class.java)


        viewModel.movieList.observe(this, Observer {
            myAdapter.setMovies(it)
        })


        viewModel.loading.observe(this, Observer {
            if (it){

            }
        })

        viewModel.getAllMovies()

    }
}