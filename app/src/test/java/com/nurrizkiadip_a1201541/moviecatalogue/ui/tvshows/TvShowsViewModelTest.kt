package com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nurrizkiadip_a1201541.moviecatalogue.data.Repository
import com.nurrizkiadip_a1201541.moviecatalogue.utils.MoviesData
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowsViewModelTest {

    private lateinit var viewModel: TvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var tvShowObserver: Observer<TvsViewModelState>

    private val dummyTvShows = MoviesData.generateTvShowsData()

    @Before
    fun setUp() {
        viewModel = TvShowsViewModel(repository)
    }

    @Test
    fun getTvShowsData() {
        val tvState = MutableLiveData<TvsViewModelState>()
        tvState.value = SuccessTvs(dummyTvShows)

        Mockito.`when`(repository.getAllTvShow()).thenReturn(tvState)

        val tvShowEntities = viewModel.getAllTvShows().value as SuccessTvs
        verify(repository).getAllTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.listTvs.size)

        viewModel.getAllTvShows().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(tvState.value)
    }
}