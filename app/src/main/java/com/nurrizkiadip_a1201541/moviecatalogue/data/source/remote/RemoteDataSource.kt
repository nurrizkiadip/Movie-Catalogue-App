package com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nurrizkiadip_a1201541.moviecatalogue.utils.EspressoIdlingResource
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.network.ApiConfig
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.network.ApiService
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.MovieResponse
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.TvDetailResponse
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.TvShowResponse
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

    fun getAllMovies(): LiveData<ApiResponse<MovieResponse>> {
        val movieList = MutableLiveData<ApiResponse<MovieResponse>>()

        EspressoIdlingResource.increment()
        apiService.getMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val responseMovies = response.body()
                    if (responseMovies != null) {
                        movieList.postValue(SuccessResponse(responseMovies))
                    } else {
                        Log.d(TAG, "onResponse: Unsuccessfull ${response.message()}")
                        movieList.postValue(EmptyResponse(null,"No Movies Data"))
                    }
                } else {
                    Log.d(TAG, "onResponse: Unsuccessfull ${response.message()}")
                    movieList.postValue(EmptyResponse(null, "No Movies Data"))
                }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                movieList.postValue(ErrorResponse("Cannot getting data"))
            }
        })
        EspressoIdlingResource.decrement()

        return movieList
    }

    fun getAllTvShows(): LiveData<ApiResponse<TvShowResponse>> {
        val tvList = MutableLiveData<ApiResponse<TvShowResponse>>()

        EspressoIdlingResource.increment()
        apiService.getTvShows().enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if (response.isSuccessful) {
                    val responseTvs = response.body()
                    if (responseTvs != null) {
                        tvList.postValue(SuccessResponse(responseTvs))
                    } else {
                        tvList.postValue(EmptyResponse(null,"No Tvs Data"))
                    }
                } else {
                    Log.d(TAG, "onResponse: Unsuccessfull ${response.message()}")
                    tvList.postValue(EmptyResponse(null, "No Tvs Data"))
                }
            }
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                tvList.postValue(ErrorResponse("Cannot getting data"))
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        EspressoIdlingResource.decrement()

        return tvList
    }

    fun getMovieById(id: Int): LiveData<ApiResponse<MovieDetailResponse>> {
        val movie = MutableLiveData<ApiResponse<MovieDetailResponse>>()

        EspressoIdlingResource.increment()
        apiService.getMovieById(id = id).enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val responseMovie = response.body()
                    if(responseMovie != null){
                        movie.postValue(SuccessResponse(responseMovie))
                    } else {
                        Log.d(TAG, "onResponse: Unsuccessfull ${response.message()}")
                        movie.postValue(EmptyResponse(null, "No Movie Data"))
                    }

                } else {
                    Log.d(TAG, "onResponse: Unsuccessfull ${response.message()}")
                    movie.postValue(EmptyResponse(null, "No Movie Data"))
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                movie.postValue(ErrorResponse("Cannot getting data"))
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        EspressoIdlingResource.decrement()

        return movie
    }

    fun getTvById(id: Int): LiveData<ApiResponse<TvDetailResponse>> {
        val tv = MutableLiveData<ApiResponse<TvDetailResponse>>()

        EspressoIdlingResource.increment()
        apiService.getTvById(id = id).enqueue(object : Callback<TvDetailResponse> {
            override fun onResponse(
                call: Call<TvDetailResponse>,
                response: Response<TvDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val responseTv = response.body()
                    if(responseTv != null){
                        tv.postValue(SuccessResponse(responseTv))
                    } else {
                        Log.d(TAG, "onResponse: Unsuccessfull ${response.message()}")
                        tv.postValue(EmptyResponse(null, "No Tv Data"))
                    }
                } else {
                    Log.d(TAG, "onResponse: Unsuccessfull ${response.message()}")
                    tv.postValue(EmptyResponse(null, "No Tv Data"))
                }
            }
            override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                tv.postValue(ErrorResponse("Cannot getting data"))
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        EspressoIdlingResource.decrement()

        return tv
    }

}