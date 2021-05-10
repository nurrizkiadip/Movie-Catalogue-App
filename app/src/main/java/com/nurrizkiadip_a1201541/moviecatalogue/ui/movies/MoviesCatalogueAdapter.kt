package com.nurrizkiadip_a1201541.moviecatalogue.ui.movies

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.nurrizkiadip_a1201541.moviecatalogue.BuildConfig.BASE_URL_MOVIEDB_IMAGE
import com.nurrizkiadip_a1201541.moviecatalogue.R
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.MovieEntity
import com.nurrizkiadip_a1201541.moviecatalogue.databinding.ItemMoviesCatalogueBinding
import com.nurrizkiadip_a1201541.moviecatalogue.ui.detail.DetailActivity
import com.nurrizkiadip_a1201541.moviecatalogue.ui.detail.DetailActivity.Companion.MOVIE_TYPE

class MoviesCatalogueAdapter: RecyclerView.Adapter<MoviesCatalogueAdapter.ViewHolder>() {

    private val listMovies = ArrayList<MovieEntity>()

    fun setMovies(listMovies: ArrayList<MovieEntity>){
        if(listMovies.isNullOrEmpty()) return
        this.listMovies.clear()
        this.listMovies.addAll(listMovies)
        notifyDataSetChanged()
        Log.d(MoviesCatalogueFragment.TAG, "setMovies: isi movie list ${listMovies[0]}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMoviesCatalogueBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int = listMovies.size

    inner class ViewHolder(private val binding: ItemMoviesCatalogueBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding){
                tvTitleMovies.text = (movie.title ?: R.string.no_data).toString()
                tvReleasedateMovies.text = (movie.releaseDate ?: R.string.no_data).toString()
                imgMovies.load(BASE_URL_MOVIEDB_IMAGE + movie.posterPath){
                    placeholder(R.drawable.account_box)
                    error(R.drawable.error)
                    crossfade(true)
                    crossfade(250)
                    transformations(RoundedCornersTransformation(8f,0f,8f,0f))
                }
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, movie.movieId)
                    intent.putExtra(DetailActivity.EXTRA_DETAIL_TYPE, MOVIE_TYPE)
                    itemView.context.startActivity(intent)
                }

            }
        }

    }
}
