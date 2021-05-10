package com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.network

import com.nurrizkiadip_a1201541.moviecatalogue.BuildConfig.API_KEY
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.MovieResponse
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.TvDetailResponse
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("3/tv/popular")
    fun getTvShows(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = 1
    ): Call<TvShowResponse>

    @GET("3/movie/{id}")
    fun getMovieById(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): Call<MovieDetailResponse>

    @GET("3/tv/{id}")
    fun getTvById(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): Call<TvDetailResponse>

}