package com.example.thelazymonday.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.thelazymonday.data.local.LocalDataSource
import com.example.thelazymonday.data.local.entity.GameNewsEntity
import com.example.thelazymonday.data.remote.RemoteDataSource
import com.example.thelazymonday.data.remote.response.ApiResponse
import com.example.thelazymonday.data.remote.response.GameNewsResponseItem
import com.example.thelazymonday.utils.AppExecutor
import com.example.thelazymonday.vo.Resource
import javax.inject.Inject

class TheLazyMondayRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutor: AppExecutor
) : TheLazyMondayDataSource {

    override fun getAllGameNews(): LiveData<Resource<PagedList<GameNewsEntity>>> {
        return object :
            NetworkBoundResource<PagedList<GameNewsEntity>, List<GameNewsResponseItem>>(appExecutor) {

            override fun shouldFetch(data: PagedList<GameNewsEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<PagedList<GameNewsEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllGameNews(), config).build()
            }

            override fun createCall(): LiveData<ApiResponse<List<GameNewsResponseItem>?>> =
                remoteDataSource.getGameNews()

            override fun saveCallResult(data: List<GameNewsResponseItem>?) {
                val movieList = ArrayList<GameNewsEntity>()

                if (data != null) {
                    for (response in data) {
                        val gameNewsEntity = GameNewsEntity(
                            0,
                            response.title.toString(),
                            response.thumb.toString(),
                            response.author.toString(),
                            response.tag.toString(),
                            response.time.toString(),
                            response.desc.toString(),
                            response.key.toString()
                        )

                        movieList.add(gameNewsEntity)
                    }
                }

                localDataSource.insertGameNews(movieList)
            }

            override fun onFetchFailed() {
                Log.e("TlmRepository", "Something Wrong")
            }
        }.asLiveData()
    }
}