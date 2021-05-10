package com.nurrizkiadip_a1201541.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nurrizkiadip_a1201541.moviecatalogue.data.Repository
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.MoviesViewModelState
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.SuccessMovies
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.SuccessTvs
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.TvsViewModelState
import com.nurrizkiadip_a1201541.moviecatalogue.utils.MoviesData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository
    @Mock
    private lateinit var observerMovie: Observer<MoviesViewModelState>
    @Mock
    private lateinit var observerTv: Observer<TvsViewModelState>

    private val dummyMovie = MoviesData.generateMovieData()[0]
    private val movieId = dummyMovie.movieId?.toInt()
    private val dummyTvShow = MoviesData.generateTvShowsData()[0]
    private val tvId = dummyTvShow.tvId?.toInt()


    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getMovieDetail() {
        val movieState = MutableLiveData<MoviesViewModelState>()
        movieState.value = SuccessMovies(listOf(dummyMovie))

        Mockito.`when`(repository.getMovieById(movieId as Int)).thenReturn(movieState)

        val movieResponse = viewModel.getMovieDetail(movieId).value as SuccessMovies
        val movieEntity = movieResponse.listMovies[0]
        verify(repository).getMovieById(movieId)
        assertNotNull(movieEntity)

        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.posterPath, movieEntity.posterPath)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.popularity, movieEntity.popularity)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.originalLanguage, movieEntity.originalLanguage)

        viewModel.getMovieDetail(movieId).observeForever(observerMovie)
        verify(observerMovie).onChanged(movieState.value)

    }

    @Test
    fun getTvShowDetail() {
        val tvState = MutableLiveData<TvsViewModelState>()
        tvState.value = SuccessTvs(listOf(dummyTvShow))

        Mockito.`when`(repository.getTvById(tvId as Int)).thenReturn(tvState)

        val tvResponse = viewModel.getTvShowDetail(tvId).value as SuccessTvs
        val tvEntity = tvResponse.listTvs[0]
        assertNotNull(tvEntity)

        assertEquals(dummyTvShow.tvId, dummyTvShow.tvId)
        assertEquals(dummyTvShow.posterPath, dummyTvShow.posterPath)
        assertEquals(dummyTvShow.title, dummyTvShow.title)
        assertEquals(dummyTvShow.overview, dummyTvShow.overview)
        assertEquals(dummyTvShow.popularity, dummyTvShow.popularity)
        assertEquals(dummyTvShow.firstAirDate, dummyTvShow.firstAirDate)
        assertEquals(dummyTvShow.number_of_episodes, dummyTvShow.number_of_episodes)
        assertEquals(dummyTvShow.number_of_seasons, dummyTvShow.number_of_seasons)
        assertEquals(dummyTvShow.originalLanguage, dummyTvShow.originalLanguage)

        viewModel.getTvShowDetail(tvId).observeForever(observerTv)
        verify(observerTv).onChanged(tvState.value)
    }

}