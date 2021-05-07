package com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshows

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nurrizkiadip_a1201541.moviecatalogue.databinding.FragmentTvShowsCatalogueBinding
import com.nurrizkiadip_a1201541.moviecatalogue.ui.home.viewmodel.ViewModelFactory
import com.nurrizkiadip_a1201541.moviecatalogue.utils.gone
import com.nurrizkiadip_a1201541.moviecatalogue.utils.visible

class TvShowsCatalogueFragment : Fragment(){

    private var _binding: FragmentTvShowsCatalogueBinding? = null
    private val binding get() = _binding as FragmentTvShowsCatalogueBinding

    private lateinit var adapter: TvShowsAdapter
    private lateinit var viewModel: TvShowsViewModel
    var application: Application? = null

    companion object{
        private val TAG: String = TvShowsCatalogueFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowsCatalogueBinding.inflate(inflater, container, false)
        adapter = TvShowsAdapter()
        val factory = ViewModelFactory.getInstance(this.application as Application)
        viewModel = ViewModelProvider(requireActivity(), factory)[TvShowsViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visible()
        if(activity != null){
            populateTvs()
        }
    }

    private fun populateTvs() {
        binding.rvTvshow.layoutManager = LinearLayoutManager(context)
        binding.rvTvshow.setHasFixedSize(true)
        binding.rvTvshow.adapter = adapter

        viewModel.getAllTvShows().observe(viewLifecycleOwner){
            if (it != null){
                when(it){
                    is SuccessTvs -> {
                        Log.d(TAG, "populateTvs: list tv: \n${it.listTvs[0]}")
                        Toast.makeText(requireActivity(),
                            "Success Collecting Data",
                            Toast.LENGTH_SHORT).show()
                        binding.tvNoData.gone()
                        binding.imgNoData.gone()
                        adapter.setTvShows(ArrayList(it.listTvs))
                    }
                    is EmptyTvs -> {
                        Log.d(TAG, "populateTvs: list tv: \n${it.message}")
                        Toast.makeText(requireActivity(), "No Data Collected", Toast.LENGTH_SHORT).show()
                        binding.tvNoData.text = it.message
                        binding.imgNoData.visible()
                        binding.tvNoData.visible()
                    }
                    is ErrorTvs -> {
                        Log.d(TAG, "populateTvs: list tv: \n${it.message}")
                        Toast.makeText(requireActivity(), "Error collecting data", Toast.LENGTH_SHORT).show()
                        binding.tvNoData.text = it.message
                        binding.imgNoData.visible()
                        binding.tvNoData.visible()
                    }
                    is Tvs -> {
                        adapter.setTvShows(arrayListOf())
                    }
                }
                binding.progressBar.gone()

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}