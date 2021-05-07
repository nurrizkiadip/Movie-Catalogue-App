package com.nurrizkiadip_a1201541.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.RemoteDataSource
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.MoviesViewModelState
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.TvsViewModelState

class Repository(private val remoteData: RemoteDataSource): MovieDataSource {

    companion object {
        @Volatile private var instance: Repository? = null

        fun getInstance(remoteData: RemoteDataSource): Repository{
            return instance ?: synchronized(this){
                instance ?: Repository(remoteData).apply { instance = this }
            }
        }
    }

    override fun getAllMovies(): LiveData<MoviesViewModelState> {
        var movieList = MutableLiveData<MoviesViewModelState>()

        remoteData.getAllMovies(object: RemoteDataSource.LoadAllMoviesCallback{
            override fun onAllMoviesReceived(movieResponse: MutableLiveData<MoviesViewModelState>) {
                movieList = movieResponse
            }
        })

        return movieList
    }

    override fun getAllTvShow(): LiveData<TvsViewModelState> {
        var tvList = MutableLiveData<TvsViewModelState>()

        remoteData.getAllTvShows(object: RemoteDataSource.LoadAllTvsCallback{
            override fun onAllTvsReceived(tvResponse: MutableLiveData<TvsViewModelState>) {
                tvList = tvResponse
            }
        })

        return tvList
    }

    override fun getMovieById(id: Int): LiveData<MoviesViewModelState> {
        var movie = MutableLiveData<MoviesViewModelState>()

        remoteData.getMovieById(id, object: RemoteDataSource.LoadMovieByIdCallback{
            override fun onMovieByIdReceived(movieResponse: MutableLiveData<MoviesViewModelState>) {
                movie = movieResponse
            }
        })

        return movie
    }

    override fun getTvById(id: Int): LiveData<TvsViewModelState> {
        var tv = MutableLiveData<TvsViewModelState>()
        remoteData.getTvById(id, object: RemoteDataSource.LoadTvByIdCallback{
            override fun onTvByIdReceived(tvResponse: MutableLiveData<TvsViewModelState>) {
                tv = tvResponse
            }
        })

        return tv
    }
}