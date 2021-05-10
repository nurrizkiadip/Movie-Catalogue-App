package com.nurrizkiadip_a1201541.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.RoundedCornersTransformation
import com.nurrizkiadip_a1201541.moviecatalogue.BuildConfig.BASE_URL_MOVIEDB_IMAGE
import com.nurrizkiadip_a1201541.moviecatalogue.R
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.MovieEntity
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.TvShowEntity
import com.nurrizkiadip_a1201541.moviecatalogue.databinding.ActivityDetailBinding
import com.nurrizkiadip_a1201541.moviecatalogue.databinding.ContentDetailBinding
import com.nurrizkiadip_a1201541.moviecatalogue.viewmodel.ViewModelFactory
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.*
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.*
import com.nurrizkiadip_a1201541.moviecatalogue.utils.*
import com.nurrizkiadip_a1201541.moviecatalogue.vo.Resource
import com.nurrizkiadip_a1201541.moviecatalogue.vo.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var bindingContentDetail: ContentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var menu: Menu? = null

    companion object {
        private val TAG: String = DetailActivity::class.java.simpleName
        const val MOVIE_TYPE = "movie_type"
        const val TV_TYPE = "tv_type"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_DETAIL_TYPE = "extra_detail_type"
        const val EXTRA_IS_FAVORITE = "extra_is_favorite"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        bindingContentDetail = binding.detailContent
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            elevation = 4f
        }

        val id = intent.getStringExtra(EXTRA_ID)
        Log.d(TAG, "onCreate: id: $id")
        val type = intent.getStringExtra(EXTRA_DETAIL_TYPE)
        Log.d(TAG, "onCreate: type: $type")
        val isFavorite = intent.getBooleanExtra(EXTRA_IS_FAVORITE, false)
        Log.d(TAG, "onCreate: isFavorite: $isFavorite")

        val factory = ViewModelFactory.getInstance(this, application, lifecycleScope)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        binding.progressBar.visible()
        if(!isFavorite){
            if(type == MOVIE_TYPE){
                viewModel.setType(type)
                if(id != null){
                    viewModel.setId(id)
                    viewModel.getMovieDetail().observe(this){
                        if(it != null) setMovieDataDetail(it)
                    }
                }
            }
            else if(type == TV_TYPE){
                viewModel.setType(type)
                if(id != null){
                    viewModel.setId(id)
                    viewModel.getTvShowDetail().observe(this){
                        if(it != null) setTvDataDetail(it)
                    }
                }
            }
        } else {
            if(type == MOVIE_TYPE){
                viewModel.setType(type)
                if(id != null){
                    viewModel.setId(id)
                    viewModel.getMovieDetailFavorite().observe(this){
                        if(it != null) populateMovieDataContentDetail(it)
                    }
                }
            }
            else if(type == TV_TYPE){
                viewModel.setType(type)
                if(id != null){
                    viewModel.setId(id)
                    viewModel.getTvShowDetailFavorite().observe(this){
                        if(it != null) populateTvShowDataContentDetail(it)
                    }
                }
            }
        }
    }

    private fun setMovieDataDetail(movieState: Resource<MovieEntity>){
        when(movieState.status){
            Status.SUCCESS -> {
                if(movieState.data != null){
                    Toast.makeText(this, "Success Collecting Data", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "populateMovies: isi movie list ${movieState.data}")

                    binding.containerContent.visible()
                    binding.tvNoDataDetail.gone()
                    binding.imgNoDataDetail.gone()
                    populateMovieDataContentDetail(movieState.data)
                } else {
                    Toast.makeText(this, "No Data Collected", Toast.LENGTH_SHORT).show()
                    binding.tvNoDataDetail.text = movieState.message
                    binding.containerContent.gone()
                    binding.imgNoDataDetail.visible()
                    binding.tvNoDataDetail.visible()
                }

            }
            Status.LOADING ->{
                binding.progressBar.visible()
                binding.tvNoDataDetail.gone()
                binding.imgNoDataDetail.gone()
            }
            Status.ERROR -> {
                Toast.makeText(this, "Error collecting data", Toast.LENGTH_SHORT).show()
                binding.tvNoDataDetail.text = movieState.message
                binding.containerContent.gone()
                binding.imgNoDataDetail.visible()
                binding.tvNoDataDetail.visible()
            }
        }
        binding.progressBar.gone()
    }

    private fun setTvDataDetail(tvState: Resource<TvShowEntity>){
        when(tvState.status){
            Status.SUCCESS -> {
                if(tvState.data != null){
                    Toast.makeText(this, "Success Collecting Data", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "populateMovies: isi movie list ${tvState.data}")

                    binding.containerContent.visible()
                    binding.tvNoDataDetail.gone()
                    binding.imgNoDataDetail.gone()
                    populateTvShowDataContentDetail(tvState.data)
                } else {
                    Toast.makeText(this, "No Data Collected", Toast.LENGTH_SHORT).show()
                    binding.tvNoDataDetail.text = tvState.message
                    binding.containerContent.gone()
                    binding.imgNoDataDetail.visible()
                    binding.tvNoDataDetail.visible()
                }

            }
            Status.LOADING ->{
                binding.progressBar.visible()
                binding.tvNoDataDetail.gone()
                binding.imgNoDataDetail.gone()

            }
            Status.ERROR -> {
                Toast.makeText(this, "Error collecting data", Toast.LENGTH_SHORT).show()
                binding.tvNoDataDetail.text = tvState.message
                binding.containerContent.gone()
                binding.imgNoDataDetail.visible()
                binding.tvNoDataDetail.visible()
            }
        }
        binding.progressBar.gone()
    }

    private fun populateMovieDataContentDetail(movie: MovieEntity){
        with(bindingContentDetail){
            imageView.load(BASE_URL_MOVIEDB_IMAGE + movie.posterPath){
                placeholder(R.drawable.account_box)
                error(R.drawable.error)
                crossfade(true)
                crossfade(200)
                transformations(RoundedCornersTransformation(8f))
            }
            tvIdDetail.text = (movie.movieId ?: R.string.no_data).toString()
            tvTitleDetail.text = (movie.title ?: R.string.no_data).toString()
            tvReleaseDetail.text = (movie.releaseDate ?: R.string.no_data).toString()
            tvPopularityDetail.text = (movie.popularity ?: R.string.no_data).toString()
            tvOverviewDetail.text = (movie.overview ?: R.string.no_data).toString()
            tvLanguageDetail.text = (movie.originalLanguage?.toEngLang() ?: R.string.no_data).toString()

        }
    }

    private fun populateTvShowDataContentDetail(tvshow: TvShowEntity){
        with(bindingContentDetail){
            nmbOfEps.visibility = View.VISIBLE
            nmbOfSeas.visibility = View.VISIBLE
            imageView.load(BASE_URL_MOVIEDB_IMAGE + tvshow.posterPath){
                placeholder(R.drawable.account_box)
                error(R.drawable.error)
                crossfade(true)
                crossfade(200)
                transformations(RoundedCornersTransformation(8f))
            }
            tvIdDetail.text = tvshow.tvId ?: R.string.no_data.toString()
            tvTitleDetail.text = tvshow.title ?: R.string.no_data.toString()
            tvReleaseDetail.text = tvshow.firstAirDate ?: R.string.no_data.toString()
            tvPopularityDetail.text = tvshow.popularity ?: R.string.no_data.toString()
            tvOverviewDetail.text = tvshow.overview ?: R.string.no_data.toString()
            tvNumberOfEps.text = (tvshow.numberOfEpisodes ?: R.string.no_data.toString()).toString()
            tvNumberOfSeasons.text = (tvshow.numberOfSeasons ?: R.string.no_data.toString()).toString()
            tvLanguageDetail.text = (tvshow.originalLanguage?.toEngLang() ?: R.string.no_data.toString()).toString()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu

        if(viewModel.type.value == MOVIE_TYPE){
            viewModel.getMovieDetailFavorite().observe(this){
                if(it != null){
                    isFavoriteActive(it.isFavorite)
                }
            }
        } else if(viewModel.type.value == TV_TYPE){
            viewModel.getTvShowDetailFavorite().observe(this){
                if(it != null){
                    isFavoriteActive(it.isFavorite)
                }
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorite_action -> {
                viewModel.insertMovieFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun isFavoriteActive(state: Boolean){
        if(this.menu != null){
            val favoriteButton = this.menu?.findItem(R.id.favorite_action)
            if(state) favoriteButton?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
            else favoriteButton?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)

        }
    }
}