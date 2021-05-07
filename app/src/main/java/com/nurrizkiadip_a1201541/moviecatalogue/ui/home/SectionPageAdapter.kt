package com.nurrizkiadip_a1201541.moviecatalogue.ui.home

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nurrizkiadip_a1201541.moviecatalogue.ui.movies.MoviesCatalogueFragment
import com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows.TvShowsCatalogueFragment

class SectionPageAdapter(private val application: Application, context:AppCompatActivity)
    : FragmentStateAdapter(context) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                MoviesCatalogueFragment().apply {
                    this.application = this@SectionPageAdapter.application
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
