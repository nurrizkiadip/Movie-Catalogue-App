package com.nurrizkiadip_a1201541.moviecatalogue.ui.movies

import com.nurrizkiadip_a1201541.moviecatalogue.data.MovieEntity

sealed class MoviesViewModelState

object Movies : MoviesViewModelState()
data class EmptyMovies(val message: String) : MoviesViewModelState()
data class SuccessMovies(val listMovies: List<MovieEntity>) : MoviesViewModelState()
data class ErrorMovies(val message: String) : MoviesViewModelState()