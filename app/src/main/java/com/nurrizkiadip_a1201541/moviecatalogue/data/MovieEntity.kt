package com.nurrizkiadip_a1201541.moviecatalogue.data

data class MovieEntity(
        var movieId: String? = null,
        var posterPath: String? = null,
        var title: String? = null,
        var overview: String? = null,
        var popularity: String? = null,
        var releaseDate: String? = null,
        var originalLanguage: String? = null,
)
