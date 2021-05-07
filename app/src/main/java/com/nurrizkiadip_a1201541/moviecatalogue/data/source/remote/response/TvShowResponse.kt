package com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<TvResultsItem>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

data class TvResultsItem(

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

)

data class TvDetailResponse(

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("number_of_episodes")
	val numberOfEpisodes: Int,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("number_of_seasons")
	val numberOfSeasons: Int,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("name")
	val name: String,
)