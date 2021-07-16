package com.example.thelazymonday.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.thelazymonday.data.remote.response.ApiResponse
import com.example.thelazymonday.data.remote.response.StatusResponse
import com.example.thelazymonday.utils.AppExecutor
import com.example.thelazymonday.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutor: AppExecutor) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDb()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)

            if (shouldFetch(dbSource)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.success(newData)
                }
            }
        }
    }

    abstract fun shouldFetch(dbSource: LiveData<ResultType>): Boolean

    abstract fun loadFromDb(): LiveData<ResultType>

    abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    abstract fun saveCallResult(data: RequestType?)

    abstract fun onFetchFailed()

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = Resource.loading(newData)
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when (response.status) {
                StatusResponse.SUCCESS ->
                    mExecutor.diskIO().execute {
                        saveCallResult(response.body)
                        mExecutor.mainThread().execute {
                            result.addSource(loadFromDb()) { newData ->
                                result.value = Resource.success(newData)
                            }
                        }
                    }
                StatusResponse.EMPTY -> mExecutor.mainThread().execute {
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.success(newData)

                    }
                }

                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(response.message, newData)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result

}