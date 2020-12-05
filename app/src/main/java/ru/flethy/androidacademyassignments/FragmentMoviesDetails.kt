package ru.flethy.androidacademyassignments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.flethy.androidacademyassignments.domain.ActorsDataSource
import ru.flethy.androidacademyassignments.domain.MoviesDataSource
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
            val movieId = it.getInt(MOVIE_ID_KEY)
            movie = MoviesDataSource().getMovies().firstOrNull { it.id == movieId }
        }

        insertMovieData(view, movie)

    }

    override fun onStart() {
        super.onStart()
        (actorsRecyclerView?.adapter as? ActorsAdapter)?.apply {
            movie?.actors?.let { bindActors(it) }
        }
    }

    override fun onDetach() {
        super.onDetach()
        actorsRecyclerView = null
    }

    private fun insertMovieData(view: View, movie: Movie?) {
        view.findViewById<TextView>(R.id.movie_name).text = movie?.name
        view.findViewById<TextView>(R.id.movie_genre).text = movie?.genre
        view.findViewById<TextView>(R.id.movie_age).text = getString(R.string.movie_age, movie?.age)
        view.findViewById<TextView>(R.id.movie_review_count).text = getString(R.string.movie_review, movie?.reviewCount)
        view.findViewById<TextView>(R.id.movie_storyline).text = movie?.storyline
        view.findViewById<ImageView>(R.id.movie_poster).setImageResource(movie?.backgroundPoster ?: R.color.background)

        val star1 = view.findViewById<ImageView>(R.id.star_1)
        val star2 = view.findViewById<ImageView>(R.id.star_2)
        val star3 = view.findViewById<ImageView>(R.id.star_3)
        val star4 = view.findViewById<ImageView>(R.id.star_4)
        val star5 = view.findViewById<ImageView>(R.id.star_5)

        actorsRecyclerView = view.findViewById<RecyclerView>(R.id.actors_recyclerView)
        actorsRecyclerView?.adapter = ActorsAdapter()

        val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        actorsRecyclerView?.layoutManager = layoutManager

        star1.setImageResource(
                when (movie?.rating) {
                    1,2,3,4,5 -> R.drawable.ic_star
                    else -> R.drawable.ic_empty_star
                }
        )
        star2.setImageResource(
                when (movie?.rating) {
                    2,3,4,5 -> R.drawable.ic_star
                    else -> R.drawable.ic_empty_star
                }
        )
        star3.setImageResource(
                when (movie?.rating) {
                    3,4,5 -> R.drawable.ic_star
                    else -> R.drawable.ic_empty_star
                }
        )
        star4.setImageResource(
                when (movie?.rating) {
                    4,5 -> R.drawable.ic_star
                    else -> R.drawable.ic_empty_star
                }
        )
        star5.setImageResource(
                when (movie?.rating) {
                    5 -> R.drawable.ic_star
                    else -> R.drawable.ic_empty_star
                }
        )
    }

    companion object {
        const val MOVIE_ID_KEY = "movieId"
    }

}