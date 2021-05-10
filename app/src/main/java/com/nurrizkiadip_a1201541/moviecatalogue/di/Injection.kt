package com.nurrizkiadip_a1201541.moviecatalogue.di

import android.app.Application
import android.content.Context
import com.nurrizkiadip_a1201541.moviecatalogue.data.Repository
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.LocalDataSource
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.room.MovieDatabase
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineScope

object Injection {
    fun provideRepository(
        context: Context,
        application: Application,
        coroutineScope: CoroutineScope
    ): Repository{

        val database = MovieDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val remoteDataSource = RemoteDataSource.getInstance(application)

        return Repository.getInstance(remoteDataSource, localDataSource, coroutineScope)
    }
}