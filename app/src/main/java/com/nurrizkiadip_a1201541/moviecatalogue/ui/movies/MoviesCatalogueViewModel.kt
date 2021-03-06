package com.nurrizkiadip_a1201541.moviecatalogue.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.Repository

class MoviesCatalogueViewModel(
    private val repository: Repository,
): ViewModel() {

    fun getMoviesData(): LiveData<MoviesViewModelState> = repository.getAllMovies()

}
