package com.example.thelazymonday.data.local

import com.example.thelazymonday.data.local.entity.GameNewsEntity
import com.example.thelazymonday.data.local.room.TheLazyMondayDbDao
import androidx.paging.DataSource
import java.util.ArrayList
import javax.inject.Inject


class LocalDataSource @Inject constructor(private val mTheLazyMondayDbDao: TheLazyMondayDbDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mTheLazyMondayDbDao: TheLazyMondayDbDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(mTheLazyMondayDbDao)
    }

    fun getAllGameNews(): DataSource.Factory<Int, GameNewsEntity> =
        mTheLazyMondayDbDao.getAllGameNews()

    fun insertGameNews(gameNewsList: List<GameNewsEntity>) =
        mTheLazyMondayDbDao.insertGameNews(gameNewsList)
}