package com.nurrizkiadip_a1201541.moviecatalogue.ui.movies

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.EmptyResponse
import com.nurrizkiadip_a1201541.moviecatalogue.databinding.FragmentMoviesCatalogueBinding
import com.nurrizkiadip_a1201541.moviecatalogue.viewmodel.ViewModelFactory
import com.nurrizkiadip_a1201541.moviecatalogue.utils.gone
import com.nurrizkiadip_a1201541.moviecatalogue.utils.visible
import com.nurrizkiadip_a1201541.moviecatalogue.vo.ErrorResource
import com.nurrizkiadip_a1201541.moviecatalogue.vo.LoadingResource
import com.nurrizkiadip_a1201541.moviecatalogue.vo.Status
import com.nurrizkiadip_a1201541.moviecatalogue.vo.SuccessResource
import java.util.ArrayList

class MoviesCatalogueFragment : Fragment(){

    private var _binding: FragmentMoviesCatalogueBinding? = null
    private val binding get() = _binding as FragmentMoviesCatalogueBinding
    private lateinit var adapter: MoviesCatalogueAdapter
    private lateinit var viewModel: MoviesCatalogueViewModel
    var application: Application? = null
    
    companion object{
        val TAG: String = MoviesCatalogueFragment::class.java.simpleName
        const val MOVIE_FAV = "movie_fav"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesCatalogueBinding.inflate(inflater, container, false)

        adapter = MoviesCatalogueAdapter()
        val factory = ViewModelFactory.getInstance(requireActivity(), this.application as Application, lifecycleScope)
        viewModel = ViewModelProvider(requireActivity(), factory)[MoviesCatalogueViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visible()
        if(activity != null){
            populateMovies()

        }
    }

    private fun populateMovies() {
        binding.rvMovies.layoutManager = LinearLayoutManager(context)
        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies.adapter = adapter

        viewModel.getMoviesData().observe(viewLifecycleOwner){
            if(it != null) when(it.status){
                    Status.SUCCESS -> {
                        if(it.data != null){
                            Toast.makeText(requireActivity(), "Success Collecting Data", Toast.LENGTH_SHORT).show()
                            Log.d(TAG, "populateMovies: isi movie list ${it.data[0]}")

                            binding.tvNoData.gone()
                            binding.imgNoData.gone()
                            adapter.setMovies(ArrayList(it.data))
                        } else {
                            Toast.makeText(requireActivity(), "No Data Collected", Toast.LENGTH_SHORT).show()
                            binding.tvNoData.text = it.message
                            binding.imgNoData.visible()
                            binding.tvNoData.visible()
                        }
                    }
                    Status.LOADING -> {
                        binding.progressBar.visible()
                        binding.tvNoData.gone()
                        binding.imgNoData.gone()
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireActivity(), "Error collecting data", Toast.LENGTH_SHORT).show()
                        binding.tvNoData.text = it.message
                        binding.imgNoData.visible()
                        binding.tvNoData.visible()
                    }
                }
                binding.progressBar.gone()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}