package com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.MovieEntity
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentities")
    fun getAllMovieFavorite(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshowentities")
    fun getAllTvFavorite(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM movieentities WHERE movieId=:id")
    fun getMovieFavoriteById(id: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tvshowentities WHERE tvId=:id")
    fun getTvFavoriteById(id: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieFavorite(movieFav: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowFavorite(tvFav: TvShowEntity)

    @Update
    fun updateMovieFavorite(movie: MovieEntity)

    @Update
    fun updateTvFavorite(tv: TvShowEntity)
}