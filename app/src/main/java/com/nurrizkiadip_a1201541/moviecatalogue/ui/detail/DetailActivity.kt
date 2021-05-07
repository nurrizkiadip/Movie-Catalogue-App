package com.nurrizkiadip_a1201541.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.RoundedCornersTransformation
import com.nurrizkiadip_a1201541.moviecatalogue.BuildConfig.BASE_URL_MOVIEDB_IMAGE
import com.nurrizkiadip_a1201541.moviecatalogue.R
import com.nurrizkiadip_a1201541.moviecatalogue.data.MovieEntity
import com.nurrizkiadip_a1201541.moviecatalogue.data.TvShowEntity
import com.nurrizkiadip_a1201541.moviecatalogue.databinding.ActivityDetailBinding
import com.nurrizkiadip_a1201541.moviecatalogue.databinding.ContentDetailBinding
import com.nurrizkiadip_a1201541.moviecatalogue.ui.home.viewmodel.ViewModelFactory
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.*
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.*
import com.nurrizkiadip_a1201541.moviecatalogue.utils.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var bindingContentDetail: ContentDetailBinding
    private lateinit var viewModel: DetailViewModel

    companion object {
        private val TAG: String = DetailActivity::class.java.simpleName
        const val MOVIE_TYPE = "movie_type"
        const val TV_TYPE = "tv_type"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_DETAIL_TYPE = "extra_detail_type"
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
            elevation = 0f
        }

        val id = intent.getStringExtra(EXTRA_ID)
        Log.d(TAG, "onCreate: id: $id")
        val type = intent.getStringExtra(EXTRA_DETAIL_TYPE)
        Log.d(TAG, "onCreate: type: $type")

        val factory = ViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        binding.progressBar.visible()
        if(type == MOVIE_TYPE){
            viewModel.getMovieDetail(id?.toInt() as Int).observe(this){
                if(it != null) setMovieDataDetail(it)
            }
        }
        else if(type == TV_TYPE){
            viewModel.getTvShowDetail(id?.toInt() as Int).observe(this){
                if(it != null) setTvDataDetail(it)
            }
        }
    }

    private fun setMovieDataDetail(movieState: MoviesViewModelState){
        when(movieState){
            is SuccessMovies -> {
                Toast.makeText(this, "Success Collecting Data", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "populateMovies: isi movie list ${movieState.listMovies[0]}")

                binding.containerContent.visible()
                binding.tvNoDataDetail.gone()
                binding.imgNoDataDetail.gone()
                populateMovieDataContentDetail(movieState.listMovies[0])
            }
            is EmptyMovies ->{
                Toast.makeText(this, "No Data Collected", Toast.LENGTH_SHORT).show()
                binding.tvNoDataDetail.text = movieState.message
                binding.containerContent.gone()
                binding.imgNoDataDetail.visible()
                binding.tvNoDataDetail.visible()
            }
            is ErrorMovies -> {
                Toast.makeText(this, "Error collecting data", Toast.LENGTH_SHORT).show()
                binding.tvNoDataDetail.text = movieState.message
                binding.containerContent.gone()
                binding.imgNoDataDetail.visible()
                binding.tvNoDataDetail.visible()
            }
            Movies -> populateMovieDataContentDetail(MovieEntity())
        }
        binding.progressBar.gone()
    }

    private fun setTvDataDetail(tvState: TvsViewModelState){
        when(tvState){
            is SuccessTvs -> {
                Toast.makeText(this, "Success Collecting Data", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "populateMovies: isi movie list ${tvState.listTvs[0]}")

                binding.containerContent.visible()
                binding.tvNoDataDetail.gone()
                binding.imgNoDataDetail.gone()
                populateTvShowDataContentDetail(tvState.listTvs[0])
            }
            is EmptyTvs ->{
                Toast.makeText(this, "No Data Collected", Toast.LENGTH_SHORT).show()
                binding.tvNoDataDetail.text = tvState.message
                binding.containerContent.gone()
                binding.imgNoDataDetail.visible()
                binding.tvNoDataDetail.visible()
            }
            is ErrorTvs -> {
                Toast.makeText(this, "Error collecting data", Toast.LENGTH_SHORT).show()
                binding.tvNoDataDetail.text = tvState.message
                binding.containerContent.gone()
                binding.imgNoDataDetail.visible()
                binding.tvNoDataDetail.visible()
            }
            Tvs -> populateTvShowDataContentDetail(TvShowEntity())
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
            tvNumberOfEps.text = (tvshow.number_of_episodes ?: R.string.no_data.toString()).toString()
            tvNumberOfSeasons.text = (tvshow.number_of_seasons ?: R.string.no_data.toString()).toString()
            tvLanguageDetail.text = (tvshow.originalLanguage?.toEngLang() ?: R.string.no_data.toString()).toString()

        }
    }

}