package com.example.mvvmpractice

class Repo constructor(private val retrofitService: ApiInterface) {

    suspend fun getAllMovies()= retrofitService.getAllMovies()
}