package com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.Repository

class TvShowsViewModel(private val repository: Repository): ViewModel() {

    fun getAllTvShows(): LiveData<TvsViewModelState> = repository.getAllTvShow()
}
