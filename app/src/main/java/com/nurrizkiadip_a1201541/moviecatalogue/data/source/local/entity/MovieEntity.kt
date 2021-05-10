package com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class MovieEntity(
        @PrimaryKey
        @ColumnInfo(name = "movieId")
        var movieId: String,

        @ColumnInfo(name = "posterPath")
        var posterPath: String? = null,

        @ColumnInfo(name = "title")
        var title: String? = null,

        @ColumnInfo(name = "overview")
        var overview: String? = null,

        @ColumnInfo(name = "popularity")
        var popularity: String? = null,

        @ColumnInfo(name = "releaseDate")
        var releaseDate: String? = null,

        @ColumnInfo(name = "originalLanguage")
        var originalLanguage: String? = null,

        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean = false
)
