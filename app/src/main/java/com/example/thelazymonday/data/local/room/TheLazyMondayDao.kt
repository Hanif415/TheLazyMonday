package com.example.thelazymonday.data.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.example.thelazymonday.data.local.entity.GameNewsEntity

@Dao
interface TheLazyMondayDbDao {
    @Query("SELECT * FROM gameNewsEntities")
    fun getGameNews(): DataSource.Factory<Int, GameNewsEntity>
}