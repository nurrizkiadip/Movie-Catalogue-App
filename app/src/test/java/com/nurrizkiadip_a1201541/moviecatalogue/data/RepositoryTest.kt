package com.nurrizkiadip_a1201541.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.any
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.RemoteDataSource
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.MoviesViewModelState
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.SuccessMovies
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.SuccessTvs
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.TvsViewModelState
import com.nurrizkiadip_a1201541.moviecatalogue.utils.MoviesData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remoteData: RemoteDataSource

    private lateinit var fakeRepository: FakeRepository

    private val moviesResponse = MoviesData.generateMovieData()
    private val movieId = moviesResponse[0].movieId?.toInt()
    private val tvResponse = MoviesData.generateTvShowsData()
    private val tvId = tvResponse[0].tvId?.toInt()

    @Before
    fun setUp() {
        fakeRepository = FakeRepository(remoteData)
    }

    @Test
    fun getAllMovies() {
        val dummyMovieLiveData = MutableLiveData<MoviesViewModelState>()
        dummyMovieLiveData.value = SuccessMovies(moviesResponse)
        Mockito.doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadAllMoviesCallback).onAllMoviesReceived(dummyMovieLiveData)
            null
        }.`when`(remoteData).getAllMovies(any())

        val movieEntities = (fakeRepository.getAllMovies()).value as SuccessMovies
        verify(remoteData).getAllMovies(any())
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, (movieEntities).listMovies.size)
    }

    @Test
    fun getAllTvShow() {
        val dummyTvLiveData = MutableLiveData<TvsViewModelState>()
        dummyTvLiveData.value = SuccessTvs(tvResponse)

        Mockito.doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadAllTvsCallback).onAllTvsReceived(dummyTvLiveData)
            null
        }.`when`(remoteData).getAllTvShows(any())

        val tvEntities = (fakeRepository.getAllTvShow()).value as SuccessTvs
        verify(remoteData).getAllTvShows(any())
        assertNotNull(tvEntities)
        assertEquals(tvResponse.size, tvEntities.listTvs.size)
    }

    @Test
    fun getMovieById() {
        val dummyMovieLiveData = MutableLiveData<MoviesViewModelState>()
        dummyMovieLiveData.value = SuccessMovies(listOf(moviesResponse[0]))

        Mockito.doAnswer {
            (it.arguments[1] as RemoteDataSource.LoadMovieByIdCallback).onMovieByIdReceived(dummyMovieLiveData)
            null
        }.`when`(remoteData).getMovieById(ArgumentMatchers.eq(movieId as Int), any())

        val movieEntity = (fakeRepository.getMovieById(movieId)).value as SuccessMovies
        verify(remoteData).getMovieById(ArgumentMatchers.eq(movieId), any())
        assertNotNull(movieEntity)
        assertEquals(moviesResponse[0].movieId, movieEntity.listMovies[0].movieId)

    }

    @Test
    fun getTvById() {
        val dummyTvLiveData = MutableLiveData<TvsViewModelState>()
        dummyTvLiveData.value = SuccessTvs(listOf(tvResponse[0]))

        Mockito.doAnswer {
            (it.arguments[1] as RemoteDataSource.LoadTvByIdCallback).onTvByIdReceived(dummyTvLiveData)
            null
        }.`when`(remoteData).getTvById(ArgumentMatchers.eq(tvId as Int), any())

        val tvEntity = (fakeRepository.getTvById(tvId)).value as SuccessTvs
        verify(remoteData).getTvById(ArgumentMatchers.eq(tvId), any())
        assertNotNull(tvEntity)
        assertEquals(tvResponse[0].tvId, tvEntity.listTvs[0].tvId)
    }
}