package com.example.labo05

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BillboardFragment : Fragment() {

    private lateinit var button: FloatingActionButton
    private lateinit var card: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_billboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()

        button.setOnClickListener {
            it.findNavController().navigate(R.id.action_billboardFragment_to_infoMovieFragment)
        }

        card.setOnClickListener {
            it.findNavController().navigate(R.id.action_billboardFragment_to_addMovieFragment)
        }

    }

    private fun bind() {
        button = view?.findViewById(R.id.floating_action_button) as FloatingActionButton
        card = view?.findViewById(R.id.movie_1_card_view) as CardView
    }
}