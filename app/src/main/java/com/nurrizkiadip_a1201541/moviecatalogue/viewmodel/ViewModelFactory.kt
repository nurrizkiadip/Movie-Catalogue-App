package com.nurrizkiadip_a1201541.moviecatalogue.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nurrizkiadip_a1201541.moviecatalogue.data.Repository
import com.nurrizkiadip_a1201541.moviecatalogue.di.Injection
import com.nurrizkiadip_a1201541.moviecatalogue.ui.detail.DetailViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.ui.home.favorite.FavoriteViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.ui.home.home.HomeViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.MoviesCatalogueViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.TvShowsViewModel
import kotlinx.coroutines.CoroutineScope

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context, application: Application, coroutineScope: CoroutineScope): ViewModelFactory{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: ViewModelFactory(Injection.provideRepository(context,application, coroutineScope)).apply{ INSTANCE = this }
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
            modelClass.isAssignableFrom(MoviesCatalogueViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(TvShowsViewModel::class.java) -> {
                FavoriteViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
    }

}