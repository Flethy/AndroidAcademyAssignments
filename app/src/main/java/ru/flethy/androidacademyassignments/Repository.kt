package ru.flethy.androidacademyassignments

import android.content.Context
import ru.flethy.androidacademyassignments.data.JsonMovieRepository
import ru.flethy.androidacademyassignments.model.Movie

object Repository {
    var moviesList: List<Movie> = emptyList()

    suspend fun loadMoviesToRepository(context: Context){
        moviesList = JsonMovieRepository(context).loadMovies()
    }
}