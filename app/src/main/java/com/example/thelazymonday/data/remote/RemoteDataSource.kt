package com.example.thelazymonday.data.remote

import androidx.lifecycle.LiveData
import com.example.thelazymonday.data.remote.response.ApiResponse
import com.example.thelazymonday.data.remote.response.GameNewsResponseItem

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource().apply { instance = this }
            }
    }

    fun getGameNews(): LiveData<ApiResponse<List<GameNewsResponseItem>>> {

    }
}