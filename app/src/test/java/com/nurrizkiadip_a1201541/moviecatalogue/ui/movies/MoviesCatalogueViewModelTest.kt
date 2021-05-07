package com.nurrizkiadip_a1201541.moviecatalogue.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.Repository
import com.nurrizkiadip_a1201541.moviecatalogue.utils.MoviesData

@RunWith(MockitoJUnitRunner::class)
class MoviesCatalogueViewModelTest {

    private lateinit var viewModel: MoviesCatalogueViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var moviesObserver: Observer<MoviesViewModelState>

    private val dummyMovies = MoviesData.generateMovieData()

    @Before
    fun setUp() {
        viewModel = MoviesCatalogueViewModel(repository)
    }

    @Test
    fun getMoviesData() {
        val movieState = MutableLiveData <MoviesViewModelState>()
        movieState.value = SuccessMovies(dummyMovies)

        Mockito.`when`(repository.getAllMovies()).thenReturn(movieState)
        val moviesEntities = viewModel.getMoviesData().value as SuccessMovies
        verify(repository).getAllMovies()
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities.listMovies.size)

        viewModel.getMoviesData().observeForever(moviesObserver)
        verify(moviesObserver).onChanged(movieState.value)
    }
}