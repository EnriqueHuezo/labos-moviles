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
import com.example.labo05.R
import com.example.labo05.data.model.MovieModel
import com.example.labo05.data.movies
import com.example.labo05.data.qualification
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.log

class InfoMovieFragment : Fragment() {

    private lateinit var name: TextInputEditText
    private lateinit var category: TextInputEditText
    private lateinit var description: TextInputEditText
    private lateinit var calification: TextInputEditText
    private lateinit var buttonAction: Button

    private val add: MovieViewModel by viewModels {
        MovieViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind(view)
        addListeners()
    }

    private fun bind(view: View) {
        name = view.findViewById(R.id.inputText_name)
        category = view.findViewById(R.id.inputText_category)
        description = view.findViewById(R.id.inputText_description)
        calification = view.findViewById(R.id.inputText_calification)
        buttonAction = view.findViewById(R.id.action_submit_button)
    }

    private fun addListeners() {
        buttonAction.setOnClickListener {
            val movie = MovieModel(name.text.toString(), category.text.toString(), description.text.toString(), calification.text.toString())
            add.addMovies(movie)

            Log.d("Pelicula agregada", movie.toString())
            Log.d("Todas las peliculas", add.getMovies().toString())
        }
    }

}