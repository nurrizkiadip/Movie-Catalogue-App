package com.nurrizkiadip_a1201541.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.MoviesViewModelState
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.TvsViewModelState

interface MovieDataSource {
    fun getAllMovies(): LiveData<MoviesViewModelState>
    fun getAllTvShow(): LiveData<TvsViewModelState>
    fun getMovieById(id: Int): LiveData<MoviesViewModelState>
    fun getTvById(id: Int): LiveData<TvsViewModelState>
}