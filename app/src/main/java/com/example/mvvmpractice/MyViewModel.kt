package com.example.mvvmpractice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import okhttp3.Dispatcher

class MyViewModel constructor(private val myRepository: Repo): ViewModel() {


    val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    val loading = MutableLiveData<Boolean>()

    fun getAllMovies(){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = myRepository.getAllMovies()
            withContext(Dispatchers.Main){
                if (response.isSuccessful) {
                    movieList.postValue(response.body())
                    loading.value = false
                }else{
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String){
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}