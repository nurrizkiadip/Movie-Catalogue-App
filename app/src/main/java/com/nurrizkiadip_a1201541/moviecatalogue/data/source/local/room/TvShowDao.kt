package com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.MovieEntity

@Dao
interface TvShowDao {

    @Query("SELECT * FROM movieentities")
    fun getAllMovieFavorite(): DataSource.Factory<Int, MovieEntity>

}