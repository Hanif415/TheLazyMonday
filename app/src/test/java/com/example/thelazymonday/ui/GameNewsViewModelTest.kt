package com.example.thelazymonday.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.thelazymonday.data.TheLazyMondayRepository
import com.example.thelazymonday.data.local.entity.GameNewsEntity
import com.example.thelazymonday.ui.gamenews.GameNewsViewModel
import com.example.thelazymonday.vo.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GameNewsViewModelTest {

    private lateinit var viewModel: GameNewsViewModel

    // we use instant executor rule because we use asynchronous
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var theLazyMondayRepository: TheLazyMondayRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<GameNewsEntity>>>

    @Before
    fun setUp() {
        viewModel = GameNewsViewModel(theLazyMondayRepository)
    }

    @Mock
    private lateinit var pagedList: PagedList<GameNewsEntity>

    @Test
    fun getGameNews() {
        val dummyGameNews = Resource.success(pagedList)
        `when`(dummyGameNews.data?.size).thenReturn(5)

        val gameNews = MutableLiveData<Resource<PagedList<GameNewsEntity>>>()
        gameNews.value = dummyGameNews

        `when`(theLazyMondayRepository.getAllGameNews()).thenReturn(gameNews)

        val gameNewsEntity = viewModel.getGameNews().value?.data

        verify(theLazyMondayRepository).getAllGameNews()
        assertNotNull(gameNewsEntity)
        assertEquals(5, gameNewsEntity?.size)

        viewModel.getGameNews().observeForever(observer)
        verify(observer).onChanged(dummyGameNews)
    }
}