package com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.nurrizkiadip_a1201541.moviecatalogue.BuildConfig.BASE_URL_MOVIEDB_IMAGE
import com.nurrizkiadip_a1201541.moviecatalogue.R
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.local.entity.TvShowEntity
import com.nurrizkiadip_a1201541.moviecatalogue.databinding.ItemTvShowsBinding
import com.nurrizkiadip_a1201541.moviecatalogue.ui.detail.DetailActivity
import com.nurrizkiadip_a1201541.moviecatalogue.ui.detail.DetailActivity.Companion.TV_TYPE
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.MoviesCatalogueFragment

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder>(){

    private val listTvShows = ArrayList<TvShowEntity>()

    fun setTvShows(listTvShow: ArrayList<TvShowEntity>){
        if(listTvShow.isNullOrEmpty()) return
        this.listTvShows.clear()
        this.listTvShows.addAll(listTvShow)
        notifyDataSetChanged()
        Log.d(MoviesCatalogueFragment.TAG, "setMovies: isi list ${this.listTvShows[0]}")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder(ItemTvShowsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShows[position]
        holder.bind(tvShow)

    }

    override fun getItemCount(): Int = listTvShows.size

    inner class TvShowViewHolder(private val binding: ItemTvShowsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding){
                tvTitleTvshows.text = (tvShow.title ?: R.string.no_data).toString()
                tvReleasedateTvshows.text = (tvShow.firstAirDate ?: R.string.no_data).toString()
                imgTvshows.load(BASE_URL_MOVIEDB_IMAGE + tvShow.posterPath){
                    placeholder(R.drawable.account_box)
                    error(R.drawable.error)
                    crossfade(true)
                    crossfade(250)
                    transformations(RoundedCornersTransformation(8f,0f,8f,0f))
                }
                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, tvShow.tvId)
                    intent.putExtra(DetailActivity.EXTRA_DETAIL_TYPE, TV_TYPE)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }

}
