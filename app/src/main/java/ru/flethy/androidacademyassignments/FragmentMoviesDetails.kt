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
            movie = Repository.moviesList.find { it.id == movieId }
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
        actorsRecyclerView = null
        super.onDetach()
    }

    private fun insertMovieData(view: View, movie: Movie?) {

        if (movie != null) {
            view.findViewById<TextView>(R.id.movie_name).text = movie.title
            view.findViewById<TextView>(R.id.movie_genre).text = movie.genres.joinToString { it.name }
            view.findViewById<TextView>(R.id.movie_age).text = getString(R.string.movie_age, movie.pgAge)
            view.findViewById<TextView>(R.id.movie_review_count).text = getString(R.string.movie_review, movie.reviewCount)
            view.findViewById<TextView>(R.id.movie_storyline).text = movie.storyLine
            view.findViewById<ImageView>(R.id.movie_poster).load(movie.detailImageUrl)

            val star1 = view.findViewById<ImageView>(R.id.star_1)
            val star2 = view.findViewById<ImageView>(R.id.star_2)
            val star3 = view.findViewById<ImageView>(R.id.star_3)
            val star4 = view.findViewById<ImageView>(R.id.star_4)
            val star5 = view.findViewById<ImageView>(R.id.star_5)

            val starViews = listOf(star1, star2, star3, star4, star5)
            setRating(movie.rating, starViews)

            actorsRecyclerView = view.findViewById(R.id.actors_recyclerView)
            val adapter = ActorsAdapter()
            actorsRecyclerView?.adapter = adapter

            val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            actorsRecyclerView?.layoutManager = layoutManager
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