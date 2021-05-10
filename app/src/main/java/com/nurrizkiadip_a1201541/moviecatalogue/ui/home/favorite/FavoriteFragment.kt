package com.nurrizkiadip_a1201541.moviecatalogue.ui.home.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.nurrizkiadip_a1201541.moviecatalogue.databinding.FragmentFavoriteBinding
import com.nurrizkiadip_a1201541.moviecatalogue.ui.home.home.HomeViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.ui.home.home.SectionPageAdapter
import com.nurrizkiadip_a1201541.moviecatalogue.utils.MoviesData.TAB_TITLES

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding as FragmentFavoriteBinding
    private lateinit var sectionPageAdapter: SectionPageFavoriteAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpTabLayout()
    }

    private fun setUpTabLayout() {
        sectionPageAdapter = SectionPageFavoriteAdapter(this)
        binding.viewPager2.adapter = sectionPageAdapter

        val tabLayoutMediator = TabLayoutMediator(binding.tabs, binding.viewPager2){tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }
        tabLayoutMediator.attach()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}