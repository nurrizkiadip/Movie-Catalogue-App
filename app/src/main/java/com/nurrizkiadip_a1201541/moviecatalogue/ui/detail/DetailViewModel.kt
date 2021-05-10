package com.nurrizkiadip_a1201541.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.data.Repository
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.MovieEntity
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.TvShowEntity
import com.nurrizkiadip_a1201541.moviecatalogue.vo.Resource

class DetailViewModel (private val repository: Repository) : ViewModel() {
    var id = MutableLiveData<String>()
    var type = MutableLiveData<String>()
    var isFavorite = MutableLiveData<Boolean>()

    fun setId(id: String){
        this.id.value = id
    }
    fun setType(type: String){
        this.type.value = type
    }
    fun setIsFavorite(isFav: Boolean){
        this.isFavorite.value = isFav
    }

    fun getMovieDetail(): LiveData<Resource<MovieEntity>> = Transformations.switchMap(id){
        repository.getMovieById(it.toInt())
    }

    fun getTvShowDetail(): LiveData<Resource<TvShowEntity>> = Transformations.switchMap(id) {
        repository.getTvById(it.toInt())
    }

    fun getMovieDetailFavorite(): LiveData<MovieEntity> = Transformations.switchMap(id){
        repository.getMovieFavotireById(it.toInt())
    }

    fun getTvShowDetailFavorite(): LiveData<TvShowEntity> = Transformations.switchMap(id){
        repository.getTvFavotireById(it.toInt())
    }

    fun insertMovieFavorite(){
        val movie = getMovieDetailFavorite().value
        if(movie != null){
            movie.isFavorite = !movie.isFavorite
            repository.insertMovieFavorite(movie)
        }
    }

    fun insertTvFavorite(){
        val tv = getTvShowDetailFavorite().value
        if(tv != null){
            tv.isFavorite = !tv.isFavorite
            repository.insertTvFavorite(tv)
        }
    }
}
