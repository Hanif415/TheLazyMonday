package com.example.thelazymonday.ui.gamenews

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.thelazymonday.data.TheLazyMondayRepository
import com.example.thelazymonday.data.local.entity.GameNewsEntity
import com.example.thelazymonday.vo.Resource
import javax.inject.Inject

class GameNewsViewModel @Inject constructor(private val theLazyMondayRepository: TheLazyMondayRepository) :
    ViewModel() {

    fun getGameNews(): LiveData<Resource<PagedList<GameNewsEntity>>> =
        theLazyMondayRepository.getAllGameNews()

}