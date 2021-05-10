package com.nurrizkiadip_a1201541.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.MovieEntity
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.TvShowEntity
import com.nurrizkiadip_a1201541.moviecatalogue.vo.Resource

interface MovieDataSource {
    fun getAllMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getAllTvShow(): LiveData<Resource<List<TvShowEntity>>>
    fun getMovieById(id: Int): LiveData<Resource<MovieEntity>>
    fun getTvById(id: Int): LiveData<Resource<TvShowEntity>>

    fun getAllMoviesFavotire(): LiveData<PagedList<MovieEntity>>
    fun getAllTvFavotire(): LiveData<PagedList<TvShowEntity>>
    fun getMovieFavotireById(id: Int): LiveData<MovieEntity>
    fun getTvFavotireById(id: Int): LiveData<TvShowEntity>
    fun insertMovieFavorite(movieEntity: MovieEntity)
    fun insertTvFavorite(tvShowEntity: TvShowEntity)
    fun updateMovieFavorite(movieEntity: MovieEntity)
    fun updateTvFavorite(tvShowEntity: TvShowEntity)

}