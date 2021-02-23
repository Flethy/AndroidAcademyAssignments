package ru.flethy.androidacademyassignments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import ru.flethy.androidacademyassignments.model.Movie

class FragmentMoviesDetails : Fragment() {

    private var actorsRecyclerView: RecyclerView? = null
    private var movie: Movie? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)

        view?.findViewById<TextView>(R.id.back)
                ?.setOnClickListener {
                        fragmentManager?.popBackStack()
                    }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            movie = it.getSerializable(MOVIE_ID_KEY) as Movie?
        }
        if (movie == null) {
            Snackbar.make(view, R.string.error_load_movie_details, Snackbar.LENGTH_SHORT).show()
        } else {
            insertMovieData(view)
            val actorsAdapter: ActorsAdapter = actorsRecyclerView?.adapter as ActorsAdapter
            actorsAdapter.apply {
                movie?.actors?.let { bindActors(it) }
            }
        }

    }

    override fun onDetach() {
        actorsRecyclerView = null
        super.onDetach()
    }

    private fun insertMovieData(view: View) {

        val currentMovie = movie

        if (currentMovie != null) {
            view.findViewById<TextView>(R.id.movie_name).text = currentMovie.title
            view.findViewById<TextView>(R.id.movie_genre).text = currentMovie.genres.joinToString { it.name }
            view.findViewById<TextView>(R.id.movie_age).text = getString(R.string.movie_age, currentMovie.pgAge)
            view.findViewById<TextView>(R.id.movie_review_count).text = getString(R.string.movie_review, currentMovie.reviewCount)
            view.findViewById<TextView>(R.id.movie_storyline).text = currentMovie.storyLine
            view.findViewById<ImageView>(R.id.movie_poster).load(currentMovie.detailImageUrl)

            val star1 = view.findViewById<ImageView>(R.id.star_1)
            val star2 = view.findViewById<ImageView>(R.id.star_2)
            val star3 = view.findViewById<ImageView>(R.id.star_3)
            val star4 = view.findViewById<ImageView>(R.id.star_4)
            val star5 = view.findViewById<ImageView>(R.id.star_5)

            val starViews = listOf(star1, star2, star3, star4, star5)
            setRating(currentMovie.rating, starViews)

            actorsRecyclerView = view.findViewById(R.id.actors_recyclerView)
            actorsRecyclerView?.adapter = ActorsAdapter()
            actorsRecyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    private fun setRating(rating: Int, starViews: List<ImageView>) {
        for (i in 0 until rating) {
            starViews[i].setImageResource(R.drawable.ic_star)
        }
        for (i in rating until starViews.size) {
            starViews[i].setImageResource(R.drawable. ic_empty_star)
        }
    }

    companion object {
        const val MOVIE_ID_KEY = "movieId"
    }

}