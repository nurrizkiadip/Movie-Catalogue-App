package com.nurrizkiadip_a1201541.moviecatalogue.ui.tvshowfavorite

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nurrizkiadip_a1201541.moviecatalogue.R
import com.nurrizkiadip_a1201541.moviecatalogue.databinding.FragmentHomeBinding
import com.nurrizkiadip_a1201541.moviecatalogue.databinding.FragmentMovieFavoriteBinding
import com.nurrizkiadip_a1201541.moviecatalogue.databinding.FragmentTvShowFavoriteBinding
import com.nurrizkiadip_a1201541.moviecatalogue.ui.home.favorite.FavoriteViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.ui.home.home.HomeViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.ui.home.home.SectionPageAdapter
import com.nurrizkiadip_a1201541.moviecatalogue.ui.moviefavorite.MovieFavoriteAdapter
import com.nurrizkiadip_a1201541.moviecatalogue.utils.gone
import com.nurrizkiadip_a1201541.moviecatalogue.utils.visible

class TvShowFavoriteFragment : Fragment() {

    private var _binding: FragmentTvShowFavoriteBinding? = null
    private val binding get() = _binding as FragmentTvShowFavoriteBinding

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: TvShowFavoriteAdapter
    var application: Application? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowFavoriteBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        adapter = TvShowFavoriteAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvTvshow)

        if(activity != null){
            binding.progressBar.visible()
            viewModel.getAllTvFavorite().observe(viewLifecycleOwner){
                if(it != null){
                    binding.progressBar.gone()
                    adapter.submitList(it)

                }
            }

            binding.rvTvshow.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = this@TvShowFavoriteFragment.adapter
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val courseEntity = adapter.getSwipedData(swipedPosition)
                courseEntity?.let { viewModel.setTvFavorite(it) }

                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) {
                    courseEntity?.let { viewModel.setTvFavorite(it) }
                }
                snackbar.show()
            }
        }
    })

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}