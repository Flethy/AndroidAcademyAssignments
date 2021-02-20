package ru.flethy.androidacademyassignments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import ru.flethy.androidacademyassignments.FragmentMoviesDetails.Companion.MOVIE_ID_KEY
import ru.flethy.androidacademyassignments.data.JsonMovieRepository
import ru.flethy.androidacademyassignments.model.Movie


class FragmentMoviesList : Fragment() {

    private lateinit var movieRepository: JsonMovieRepository

    private var movies: List<Movie> = emptyList()

    private var moviesRecyclerView: RecyclerView? = null
    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

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

        movieRepository = JsonMovieRepository(requireContext())

        coroutineScope.launch {
            movies = movieRepository.loadMovies()

            launch(Dispatchers.Main) {
                val moviesAdapter: MoviesAdapter = moviesRecyclerView?.adapter as MoviesAdapter
                moviesAdapter.notifyDataSetChanged()
                moviesAdapter.apply {
                    bindMovies(movies)
                }
            }
        }
    }

    override fun onDetach() {
        moviesRecyclerView = null
        coroutineScope.cancel()
        super.onDetach()
    }

    private val clickListener = object : OnMovieClick {
        override fun onClick(movie: Movie) {
            processMovieClick(movie)
        }

        private fun processMovieClick(movie: Movie) {
                navigateToMovieDetails(movie)
        }

        private fun navigateToMovieDetails(movie: Movie) {

            val movieDetails = FragmentMoviesDetails()
            val bundle = Bundle()
            bundle.putSerializable(MOVIE_ID_KEY, movie)
            movieDetails.arguments = bundle

            fragmentManager?.let {
                it.beginTransaction()
                        .addToBackStack(null)
                        .add(R.id.container, movieDetails)
                        .commit()
            }
        }
    }

    companion object {
        const val VERTICAL_SPAN_COUNT = 2
        const val HORIZONTAL_SPAN_COUNT = 4
    }

}