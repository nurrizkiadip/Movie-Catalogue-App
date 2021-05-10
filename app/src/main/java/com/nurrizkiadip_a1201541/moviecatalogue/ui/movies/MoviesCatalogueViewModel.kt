package com.nurrizkiadip_a1201541.moviecatalogue.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.data.Repository
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.MovieEntity
import com.nurrizkiadip_a1201541.moviecatalogue.vo.Resource

class MoviesCatalogueViewModel(
    private val repository: Repository,
): ViewModel() {

    fun getMoviesData(): LiveData<Resource<List<MovieEntity>>> = repository.getAllMovies()

}
