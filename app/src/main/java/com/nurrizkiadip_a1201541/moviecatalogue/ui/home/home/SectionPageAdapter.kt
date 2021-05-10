package com.nurrizkiadip_a1201541.moviecatalogue.ui.home.home

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nurrizkiadip_a1201541.moviecatalogue.ui.home.HomeActivity.Companion.IS_MOVIE_FAV
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.MoviesCatalogueFragment
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.TvShowsCatalogueFragment

class SectionPageAdapter(
    private val application: Application,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                MoviesCatalogueFragment().apply {
                    this.application = this@SectionPageAdapter.application
                    arguments = Bundle().apply { putString(IS_MOVIE_FAV, "") }
                }
            }
            1 -> {
                TvShowsCatalogueFragment().apply {
                    this.application = this@SectionPageAdapter.application
                }
            }
            else -> Fragment()
        }
    }

}
