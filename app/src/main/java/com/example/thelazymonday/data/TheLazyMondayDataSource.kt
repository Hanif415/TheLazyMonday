package com.example.thelazymonday.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.thelazymonday.data.local.entity.GameNewsEntity
import com.example.thelazymonday.vo.Resource

interface TheLazyMondayDataSource {
    fun getAllGameNews(): LiveData<Resource<PagedList<GameNewsEntity>>>
}