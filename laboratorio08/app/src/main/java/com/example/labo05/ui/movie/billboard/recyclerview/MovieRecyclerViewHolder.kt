package com.example.labo05.ui.movie.billboard.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.labo05.data.model.MovieModel
import com.example.labo05.databinding.MovieItemBinding

class MovieRecyclerViewHolder(private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieModel, clickLIstener: (MovieModel) -> Unit) {
        binding.titleTextView.text = movie.name
        binding.scoreTextView.text = movie.qualification

        binding.movieCardView.setOnClickListener{
            clickLIstener(movie)
        }
    }
}