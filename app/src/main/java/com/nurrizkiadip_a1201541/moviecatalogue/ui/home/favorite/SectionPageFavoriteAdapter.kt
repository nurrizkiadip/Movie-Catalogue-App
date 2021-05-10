package com.nurrizkiadip_a1201541.moviecatalogue.ui.home.favorite

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nurrizkiadip_a1201541.moviecatalogue.ui.home.HomeActivity.Companion.IS_MOVIE_FAV
import com.nurrizkiadip_a1201541.moviecatalogue.ui.moviefavorite.MovieFavoriteFragment
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.MoviesCatalogueFragment
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshowfavorite.TvShowFavoriteFragment
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.TvShowsCatalogueFragment

class SectionPageFavoriteAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                MovieFavoriteFragment()
            }
            1 -> {
                TvShowFavoriteFragment()
            }
            else -> Fragment()
        }
    }

}
