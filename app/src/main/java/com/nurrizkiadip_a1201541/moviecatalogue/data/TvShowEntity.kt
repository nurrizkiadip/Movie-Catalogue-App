package com.nurrizkiadip_a1201541.moviecatalogue.data

data class TvShowEntity(
        var tvId: String? = null,
        var posterPath: String? = null,
        var title: String? = null,
        var overview: String? = null,
        var popularity: String? = null,
        var firstAirDate: String? = null,
        var number_of_episodes: Int? = null,
        var number_of_seasons: Int? = null,
        var originalLanguage: String? = null,
)