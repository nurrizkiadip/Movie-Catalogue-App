package com.nurrizkiadip_a1201541.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.LocalDataSource
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.MovieEntity
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.TvShowEntity
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.ApiResponse
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.RemoteDataSource
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.MovieResponse
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.TvDetailResponse
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.response.TvShowResponse
import com.nurrizkiadip_a1201541.moviecatalogue.vo.Resource
import com.nurrizkiadip_a1201541.moviecatalogue.vo.SuccessResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val coroutineScope: CoroutineScope
): MovieDataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            coroutineScope: CoroutineScope
        ): Repository {
            return instance ?: synchronized(this){
                instance ?: Repository(remoteData, localData, coroutineScope).apply { instance = this }
            }
        }
    }

    override fun getAllMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, MovieResponse>(coroutineScope){
            override fun shouldFetch(): Boolean = true
            override fun createCall(): LiveData<ApiResponse<MovieResponse>> {
                return remoteDataSource.getAllMovies()
            }

            override fun generateDataFromNetwork(data: MovieResponse): Resource<List<MovieEntity>> {
                val movieItems = data.results
                val listMovies = arrayListOf<MovieEntity>()
                if(movieItems.isNotEmpty()){
                    for(movie in movieItems){
                        listMovies.add(MovieEntity(
                            movie.id.toString(),
                            movie.posterPath,
                            movie.title,
                            movie.overview,
                            movie.popularity.toString(),
                            movie.releaseDate,
                            movie.originalLanguage
                        ))
                    }
                }
                return SuccessResource(listMovies)
            }
        }.asLiveData()
    }

    override fun getAllTvShow(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, TvShowResponse>(coroutineScope) {
            override fun shouldFetch(): Boolean = true

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>> {
                return remoteDataSource.getAllTvShows()
            }

            override fun generateDataFromNetwork(data: TvShowResponse): Resource<List<TvShowEntity>>? {
                val tvItems = data.results
                val listTvs = arrayListOf<TvShowEntity>()

                if(tvItems.isNotEmpty()){
                    for(tv in tvItems){
                        listTvs.add(TvShowEntity(
                            tv.id.toString(),
                            tv.posterPath,
                            tv.name,
                            tv.overview,
                            tv.popularity.toString(),
                            tv.firstAirDate,
                        ))
                    }
                }
                return SuccessResource(listTvs)
            }

        }.asLiveData()
    }

    override fun getMovieById(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(coroutineScope){
            override fun shouldFetch(): Boolean = true
            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> {
                return remoteDataSource.getMovieById(id = id)
            }

            override fun generateDataFromNetwork(data: MovieDetailResponse): Resource<MovieEntity> {

                val movieDetail = MovieEntity(
                    data.id.toString(),
                    data.posterPath,
                    data.title,
                    data.overview,
                    data.popularity.toString(),
                    data.releaseDate,
                    data.originalLanguage
                )

                return SuccessResource(movieDetail)
            }
        }.asLiveData()
    }

    override fun getTvById(id: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvDetailResponse>(coroutineScope){
            override fun shouldFetch(): Boolean = true
            override fun createCall(): LiveData<ApiResponse<TvDetailResponse>> {
                return remoteDataSource.getTvById(id = id)
            }

            override fun generateDataFromNetwork(data: TvDetailResponse): Resource<TvShowEntity> {

                val tvDetail = TvShowEntity(
                    data.id.toString(),
                    data.posterPath,
                    data.name,
                    data.overview,
                    data.popularity.toString(),
                    data.firstAirDate,
                    data.numberOfEpisodes,
                    data.numberOfSeasons,
                    data.originalLanguage
                )

                return SuccessResource(tvDetail)
            }
        }.asLiveData()
    }

    override fun getAllMoviesFavotire(): LiveData<PagedList<MovieEntity>> {

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllMoviesFavorite(), config).build()
    }

    override fun getAllTvFavotire(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllTvFavorite(), config).build()
    }

    override fun getMovieFavotireById(id: Int): LiveData<MovieEntity> {
        return localDataSource.getMovieFavoriteById(id)
    }

    override fun getTvFavotireById(id: Int): LiveData<TvShowEntity> {
        return localDataSource.getTvFavoriteById(id)
    }

    override fun insertMovieFavorite(movieEntity: MovieEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            localDataSource.insertMovieFavorite(movieEntity)
        }
    }

    override fun insertTvFavorite(tvShowEntity: TvShowEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            localDataSource.insertTvFavorite(tvShowEntity)
        }
    }

    override fun updateMovieFavorite(movieEntity: MovieEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            localDataSource.updateMovieFavotite(movieEntity)
        }
    }

    override fun updateTvFavorite(tvShowEntity: TvShowEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            localDataSource.updateTvFavotite(tvShowEntity)
        }
    }


}