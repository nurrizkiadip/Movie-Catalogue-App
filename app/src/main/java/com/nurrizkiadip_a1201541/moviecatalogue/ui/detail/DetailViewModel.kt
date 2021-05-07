package com.nurrizkiadip_a1201541.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.Repository
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.MoviesViewModelState
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.TvsViewModelState

class DetailViewModel (private val repository: Repository) : ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<MoviesViewModelState> = repository.getMovieById(movieId)

    fun getTvShowDetail(tvId: Int): LiveData<TvsViewModelState> = repository.getTvById(tvId)
}
