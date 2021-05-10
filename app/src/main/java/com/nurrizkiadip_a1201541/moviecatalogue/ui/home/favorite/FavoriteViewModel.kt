package com.nurrizkiadip_a1201541.moviecatalogue.ui.home.favorite

import androidx.lifecycle.ViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.data.Repository
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.MovieEntity
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.TvShowEntity

class FavoriteViewModel(val repository: Repository) : ViewModel() {

    fun getAllMovieFavorite() = repository.getAllMoviesFavotire()

    fun getAllTvFavorite() = repository.getAllTvFavotire()

    fun setMovieFavorite(movie: MovieEntity) {
        movie.isFavorite = !movie.isFavorite
        repository.updateMovieFavorite(movie)
    }

    fun setTvFavorite(tv: TvShowEntity) {
        tv.isFavorite = !tv.isFavorite
        repository.updateTvFavorite(tv)
    }


}