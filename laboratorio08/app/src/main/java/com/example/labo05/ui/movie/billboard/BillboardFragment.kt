package com.example.labo05.ui.movie.billboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.labo05.R
import com.example.labo05.data.model.MovieModel
import com.example.labo05.databinding.FragmentBillboardBinding
import com.example.labo05.databinding.FragmentInfoMovieBinding
import com.example.labo05.ui.movie.billboard.recyclerview.MovieRecyclerViewAdapter
import com.example.labo05.ui.movie.viewmodel.MovieViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BillboardFragment : Fragment() {

    private lateinit var adapter: MovieRecyclerViewAdapter

    private val add: MovieViewModel by activityViewModels {
        MovieViewModel.Factory
    }

    private lateinit var binding: FragmentBillboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBillboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView(view)

        binding.floatingActionButton.setOnClickListener {
            add.clearData()
            it.findNavController().navigate(R.id.action_billboardFragment_to_infoMovieFragment)
        }
    }

    private fun showSelectedItem(movie: MovieModel) {
        add.setSelectedMovie(movie)
        findNavController().navigate(R.id.action_billboardFragment_to_addMovieFragment)
    }

    private fun displayMovies() {
        adapter.setData(add.getMovies())
        adapter.notifyDataSetChanged()
    }

    private fun setRecyclerView(view: View) {
        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)

        adapter = MovieRecyclerViewAdapter { selectedMovie ->
            showSelectedItem(selectedMovie)
        }

        binding.recyclerView.adapter = adapter
        displayMovies()
    }
}