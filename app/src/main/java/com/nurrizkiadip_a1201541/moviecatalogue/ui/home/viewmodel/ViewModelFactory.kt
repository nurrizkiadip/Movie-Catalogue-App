package com.nurrizkiadip_a1201541.moviecatalogue.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.Repository
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.RemoteDataSource
import com.nurrizkiadip_a1201541.moviecatalogue.ui.detail.DetailViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.MoviesCatalogueViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.TvShowsViewModel

class ViewModelFactory(private val repository: Repository)
    : ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory{
            return instance ?: synchronized(this){
                instance ?: ViewModelFactory(Repository.getInstance(RemoteDataSource.getInstance(application))).apply{instance = this}
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MoviesCatalogueViewModel::class.java) -> {
                MoviesCatalogueViewModel(repository) as T
            }
            modelClass.isAssignableFrom(TvShowsViewModel::class.java) -> {
                TvShowsViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
    }

}