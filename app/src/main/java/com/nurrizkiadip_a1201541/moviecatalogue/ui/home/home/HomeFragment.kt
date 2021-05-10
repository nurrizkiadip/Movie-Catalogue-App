package com.nurrizkiadip_a1201541.moviecatalogue.ui.home.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.nurrizkiadip_a1201541.moviecatalogue.databinding.FragmentHomeBinding
import com.nurrizkiadip_a1201541.moviecatalogue.utils.MoviesData.TAB_TITLES

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    private lateinit var sectionPageAdapter: SectionPageAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabLayout()
    }

    private fun setUpTabLayout() {
        sectionPageAdapter = SectionPageAdapter(viewModel.application, this)
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