package com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentities")
data class TvShowEntity(
        @PrimaryKey
        @ColumnInfo(name = "tvId")
        var tvId: String,

        @ColumnInfo(name = "posterPath")
        var posterPath: String? = null,

        @ColumnInfo(name = "title")
        var title: String? = null,

        @ColumnInfo(name = "overview")
        var overview: String? = null,

        @ColumnInfo(name = "popularity")
        var popularity: String? = null,

        @ColumnInfo(name = "firstAirDate")
        var firstAirDate: String? = null,

        @ColumnInfo(name = "numberOfEpisodes")
        var numberOfEpisodes: Int? = null,

        @ColumnInfo(name = "numberOfSeasons")
        var numberOfSeasons: Int? = null,

        @ColumnInfo(name = "originalLanguage")
        var originalLanguage: String? = null,

        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean = false
)