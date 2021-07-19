package com.example.thelazymonday.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thelazymonday.data.remote.response.ApiResponse
import com.example.thelazymonday.data.remote.response.GameNewsResponseItem
import com.example.thelazymonday.service.TheLazyMondayApi
import com.example.thelazymonday.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource().apply { instance = this }
            }
    }

    fun getGameNews(): LiveData<ApiResponse<List<GameNewsResponseItem>?>> {
        EspressoIdlingResource.increment()

        val resultGameNews = MutableLiveData<ApiResponse<List<GameNewsResponseItem>?>>()
        CoroutineScope(Dispatchers.IO).launch {
            val result = TheLazyMondayApi.theLazyMondayApiService.getGameNews()
            resultGameNews.postValue(ApiResponse.success(result.gameNewsResponse))
        }

        EspressoIdlingResource.decrement()

        return resultGameNews
    }
}