package ru.flethy.androidacademyassignments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.flethy.androidacademyassignments.FragmentMoviesDetails.Companion.MOVIE_ID_KEY
import ru.flethy.androidacademyassignments.domain.MoviesDataSource
import ru.flethy.androidacademyassignments.model.Movie


class FragmentMoviesList : Fragment() {

    private var moviesRecyclerView: RecyclerView? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesRecyclerView = view.findViewById(R.id.movies_recyclerView)
        moviesRecyclerView?.adapter = MoviesAdapter(clickListener)

        val countOfColumns = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) VERTICAL_SPAN_COUNT
                             else HORIZONTAL_SPAN_COUNT
        val layoutManager = GridLayoutManager(context, countOfColumns)
        moviesRecyclerView?.layoutManager = layoutManager

    }

    override fun onStart() {
        super.onStart()
        (moviesRecyclerView?.adapter as? MoviesAdapter)?.apply {
            bindMovies(MoviesDataSource().getMovies())
        }
    }

    override fun onDetach() {
        moviesRecyclerView = null
        super.onDetach()
    }

    private val clickListener = object : OnMovieClick {
        override fun onClick(movie: Movie) {
            processMovieClick(movie)
        }

        private fun processMovieClick(movie: Movie) {
            if (movie.actors?.isEmpty() == true) {
                showIncompleteInfoMessage(movie)
            } else {
                navigateToMovieDetails(movie)
            }
        }

        private fun navigateToMovieDetails(movie: Movie) {

            val movieDetails = FragmentMoviesDetails()
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID_KEY, movie.id)
            movieDetails.arguments = bundle

            fragmentManager?.let {
                it.beginTransaction()
                        .addToBackStack(null)
                        .add(R.id.container, movieDetails)
                        .commit()
            }
        }

        private fun showIncompleteInfoMessage(movie: Movie) {
            moviesRecyclerView?.let {
                Snackbar.make(
                        it,
                        getString(R.string.fragment_movie_chosen_text, movie.name),
                        Snackbar.LENGTH_SHORT)
                        .show()
            }
        }
    }

    companion object {
        const val VERTICAL_SPAN_COUNT = 2
        const val HORIZONTAL_SPAN_COUNT = 4
    }

}