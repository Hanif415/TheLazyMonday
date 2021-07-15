package com.example.thelazymonday.data

import androidx.lifecycle.LiveData
import com.example.thelazymonday.data.local.entity.GameNewsEntity

interface TheLazyMondayDataSource {
    fun getAllGameNews(): LiveData<Resource<PagedList<GameNewsEntity>>>
}