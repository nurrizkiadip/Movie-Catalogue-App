package com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.data.Repository
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.TvShowEntity
import com.nurrizkiadip_a1201541.moviecatalogue.vo.Resource

class TvShowsViewModel(private val repository: Repository): ViewModel() {

    fun getAllTvShows(): LiveData<Resource<List<TvShowEntity>>> = repository.getAllTvShow()
}
