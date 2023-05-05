package com.example.labo05.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.labo05.R
import com.example.labo05.data.model.MovieModel
import com.example.labo05.data.movies
import com.example.labo05.data.qualification
import com.example.labo05.databinding.FragmentInfoMovieBinding
import com.example.labo05.repositories.MovieRepository
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.log

class InfoMovieFragment : Fragment() {

    private val add: MovieViewModel by viewModels {
        MovieViewModel.Factory
    }

    private lateinit var binding: FragmentInfoMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setViewModel() {
        binding.viewmodel = add
    }

    private fun observeStatus() {
        add.status.observe(viewLifecycleOwner) {status ->
            when {
                status.equals(MovieViewModel.WRONG_INFORMATION) -> {
                    Log.d("peto papito", status)
                    add.clearStatus()
                }
                status.equals(MovieViewModel.MOVIE_CREATED) -> {
                    Log.d("se creo papito", status)
                    Log.d("te creo esto: ", add.getMovies().toString())

                    add.clearStatus()
                    findNavController().popBackStack()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        observeStatus()
    }

}