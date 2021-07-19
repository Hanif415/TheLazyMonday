package com.example.thelazymonday.data.local.room


import androidx.room.Dao
import androidx.room.Query
import com.example.thelazymonday.data.local.entity.GameNewsEntity
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.room.Insert

@Dao
interface TheLazyMondayDbDao {
    @Query("SELECT * FROM gameNewsEntities")
    fun getAllGameNews(): DataSource.Factory<Int, GameNewsEntity>

    @Insert
    fun insertGameNews(gameNewsList: List<GameNewsEntity>)
}