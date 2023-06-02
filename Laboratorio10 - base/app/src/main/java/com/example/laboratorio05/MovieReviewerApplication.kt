package com.example.laboratorio05

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.laboratorio05.data.MovieReviewerDatabase
import com.example.laboratorio05.data.dao.CastDao
import com.example.laboratorio05.data.dao.MovieDao
import com.example.laboratorio05.data.model.ActorModel
import com.example.laboratorio05.data.model.CastModel
import com.example.laboratorio05.data.model.MovieModel
import com.example.laboratorio05.repositories.ActorRepository
import com.example.laboratorio05.repositories.CastRepository
import com.example.laboratorio05.repositories.MovieRepository

class MovieReviewerApplication : Application() {

    private val database: MovieReviewerDatabase by lazy {
        MovieReviewerDatabase.newInstance(this)
    }

    val movieRepository: MovieRepository by lazy {
        MovieRepository(database.movieDao())
    }

    val actorRepository: ActorRepository by lazy {
        ActorRepository(database.actorDao())
    }

    val castRepository: CastRepository by lazy {
        CastRepository(database.castDao())
    }
}