package com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nurrizkiadip_a1201541.moviecatalogue.utils.EspressoIdlingResource
import com.nurrizkiadip_a1201541.moviecatalogue.data.MovieEntity
import com.nurrizkiadip_a1201541.moviecatalogue.data.TvShowEntity
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.network.ApiConfig
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.network.ApiService
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.MovieResponse
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.MovieResultsItem
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.TvDetailResponse
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.TvShowResponse
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.EmptyMovies
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.ErrorMovies
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.MoviesViewModelState
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.SuccessMovies
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.EmptyTvs
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.ErrorTvs
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.SuccessTvs
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.TvsViewModelState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {

    companion object{
        private val TAG: String = RemoteDataSource::class.java.simpleName

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(application: Application):RemoteDataSource{
            return instance ?: synchronized(this){
                instance ?: RemoteDataSource(ApiConfig.getApiService(application)).apply { instance = this }
            }
        }
    }

    fun getAllMovies(callback: LoadAllMoviesCallback){
        val movieList = MutableLiveData<MoviesViewModelState>()
        movieList.postValue(com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.Movies)

        EspressoIdlingResource.increment()
        apiService.getMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val response1 = response.body()?.results

                    if (response1 != null && response1.isNotEmpty()) {
                        val list = ArrayList<MovieEntity>()
                        for (movie in response1) {
                            list.add(MovieEntity(
                                movie.id.toString(),
                                movie.posterPath,
                                movie.title,
                                releaseDate = movie.releaseDate,
                            ))
                        }
                        Log.d(TAG, "onResponse: pada repos isi list movie ${list[0]}")
                        movieList.postValue(SuccessMovies(list))
                    } else {
                        Log.d(TAG, "onResponse: Unsuccessfull ${response.message()}")
                        movieList.postValue(EmptyMovies("No Movies Data"))
                    }
                } else {
                    movieList.postValue(EmptyMovies("No Movies Data"))
                }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                movieList.postValue(ErrorMovies("Cannot getting data"))
            }
        })

        callback.onAllMoviesReceived(movieList)
        EspressoIdlingResource.decrement()
    }

    fun getAllTvShows(callback: LoadAllTvsCallback){
        val tvList = MutableLiveData<TvsViewModelState>()
        tvList.postValue(com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.Tvs)

        EspressoIdlingResource.increment()
        apiService.getTvShows().enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if (response.isSuccessful) {
                    val response1 = response.body()?.results

                    if (response1 != null && response1.isNotEmpty()) {
                        val list = ArrayList<TvShowEntity>()
                        for (tv in response1) {
                            list.add(TvShowEntity(
                                tv.id.toString(),
                                tv.posterPath,
                                tv.name,
                                firstAirDate = tv.firstAirDate,
                            ))
                        }
                        tvList.postValue(SuccessTvs(list))
                    } else {
                        tvList.postValue(EmptyTvs("No Tvs Data"))
                    }
                } else {
                    Log.d(TAG, "onResponse: Unsuccessfull ${response.message()}")
                    tvList.postValue(EmptyTvs("No Tvs Data"))
                }
            }
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                tvList.postValue(ErrorTvs("Cannot getting data"))
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })

        callback.onAllTvsReceived(tvList)
        EspressoIdlingResource.decrement()
    }

    fun getMovieById(id: Int, callback: LoadMovieByIdCallback){
        val movie = MutableLiveData<MoviesViewModelState>()
        movie.postValue(com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.Movies)

        EspressoIdlingResource.increment()
        apiService.getMovieById(id = id).enqueue(object : Callback<MovieResultsItem> {
            override fun onResponse(
                call: Call<MovieResultsItem>,
                response: Response<MovieResultsItem>
            ) {
                if (response.isSuccessful) {
                    val response1 = response.body()
                    if(response1 != null && response1.overview != ""){
                        movie.postValue(SuccessMovies(
                            listOf(MovieEntity(
                                response1.id.toString(),
                                response1.posterPath,
                                response1.title,
                                response1.overview,
                                response1.popularity.toString(),
                                response1.releaseDate,
                                response1.originalLanguage
                            ))
                        ))
                    } else movie.postValue(EmptyMovies("No Movie Data"))

                } else {
                    Log.d(TAG, "onResponse: Unsuccessfull ${response.message()}")
                    movie.postValue(EmptyMovies("No Movie Data"))
                }
            }

            override fun onFailure(call: Call<MovieResultsItem>, t: Throwable) {
                movie.postValue(ErrorMovies("Cannot getting data"))
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })

        callback.onMovieByIdReceived(movie)
        EspressoIdlingResource.decrement()
    }

    fun getTvById(id: Int, callback: LoadTvByIdCallback){
        val tv = MutableLiveData<TvsViewModelState>()
        tv.postValue(com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.Tvs)

        EspressoIdlingResource.increment()
        apiService.getTvById(id = id).enqueue(object : Callback<TvDetailResponse> {
            override fun onResponse(
                call: Call<TvDetailResponse>,
                response: Response<TvDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val response1 = response.body()
                    if(response1 != null && response1.overview != ""){
                        tv.postValue(SuccessTvs(
                            listOf(TvShowEntity(
                                response1.id.toString(),
                                response1.posterPath,
                                response1.name,
                                response1.overview,
                                response1.popularity.toString(),
                                response1.firstAirDate,
                                response1.numberOfEpisodes,
                                response1.numberOfSeasons,
                                response1.originalLanguage
                            ))
                        ))
                    } else tv.postValue(EmptyTvs("No Tv Data"))

                } else {
                    Log.d(TAG, "onResponse: Unsuccessfull ${response.message()}")
                    tv.postValue(EmptyTvs("No Tv Data"))
                }
            }
            override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                tv.postValue(ErrorTvs("Cannot getting data"))
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        callback.onTvByIdReceived(tv)
        EspressoIdlingResource.decrement()
    }

    interface LoadAllMoviesCallback{
        fun onAllMoviesReceived(movieResponse: MutableLiveData<MoviesViewModelState>)
    }
    interface LoadAllTvsCallback{
        fun onAllTvsReceived(tvResponse: MutableLiveData<TvsViewModelState>)
    }
    interface LoadMovieByIdCallback{
        fun onMovieByIdReceived(movieResponse: MutableLiveData<MoviesViewModelState>)
    }
    interface LoadTvByIdCallback{
        fun onTvByIdReceived(tvResponse: MutableLiveData<TvsViewModelState>)
    }
}