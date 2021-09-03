package com.example.mvvmpractice

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("movielist.json")
    suspend fun getAllMovies(): Response<List<Movie>>


    companion object{

        var retrofitService: ApiInterface? = null

        fun getInstance() : ApiInterface{

            if (retrofitService== null){
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://howtodoandroid.com/")
                    .build()

                retrofitService = retrofit.create(ApiInterface::class.java)

            }

                return retrofitService!!
        }
    }
}