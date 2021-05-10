package com.nurrizkiadip_a1201541.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.MovieEntity
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.TvShowEntity
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao) {

    companion object{
        @Volatile
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: LocalDataSource(movieDao).apply { INSTANCE = this }
            }
        }
    }

    fun getAllMoviesFavorite(): DataSource.Factory<Int, MovieEntity> = movieDao.getAllMovieFavorite()

    fun getAllTvFavorite(): DataSource.Factory<Int, TvShowEntity> = movieDao.getAllTvFavorite()

    fun getMovieFavoriteById(id: Int): LiveData<MovieEntity> = movieDao.getMovieFavoriteById(id)

    fun getTvFavoriteById(id: Int): LiveData<TvShowEntity> = movieDao.getTvFavoriteById(id)

    fun insertMovieFavorite(movie: MovieEntity) = movieDao.insertMovieFavorite(movie)

    fun insertTvFavorite(tv: TvShowEntity) = movieDao.insertTvShowFavorite(tv)

    fun updateMovieFavotite(movie: MovieEntity) = movieDao.updateMovieFavorite(movie)

    fun updateTvFavotite(tv: TvShowEntity) = movieDao.updateTvFavorite(tv)
}