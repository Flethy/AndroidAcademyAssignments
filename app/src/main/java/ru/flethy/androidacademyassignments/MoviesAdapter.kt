package ru.flethy.androidacademyassignments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.flethy.androidacademyassignments.model.Movie


class MoviesAdapter(private val clickListener: OnRecyclerItemClicked): RecyclerView.Adapter<MoviesViewHolder>() {

    private lateinit var movies: List<Movie>

    fun bindMovies(moviesList: List<Movie>) {
        movies = moviesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener{
            clickListener.onClick(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size

}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val moviePoster: ImageView = itemView.findViewById(R.id.poster)
    private val movieName: TextView = itemView.findViewById(R.id.movie_name)
    private val movieAge: TextView = itemView.findViewById(R.id.movie_age)
    private val movieGenre: TextView = itemView.findViewById(R.id.movie_genre)
    private val movieReviewCount: TextView = itemView.findViewById(R.id.movie_review_count)
    private val movieDuration: TextView = itemView.findViewById(R.id.movie_duration)
    private val star1: ImageView = itemView.findViewById(R.id.star_1)
    private val star2: ImageView = itemView.findViewById(R.id.star_2)
    private val star3: ImageView = itemView.findViewById(R.id.star_3)
    private val star4: ImageView = itemView.findViewById(R.id.star_4)
    private val star5: ImageView = itemView.findViewById(R.id.star_5)
    private val like: ImageView = itemView.findViewById(R.id.like)

    fun onBind(movie: Movie) {

        movieName.text = movie.name
        movieAge.text = "${movie.age}+"
        movieGenre.text = movie.genre
        movieReviewCount.text = "${movie.reviewCount} reviews"
        movieDuration.text = "${movie.duration} min"

        star1.setImageResource(
                when (movie.rating) {
                    1,2,3,4,5 -> R.drawable.ic_star
                    else -> R.drawable.ic_empty_star
                }
        )
        star2.setImageResource(
                when (movie.rating) {
                    2,3,4,5 -> R.drawable.ic_star
                    else -> R.drawable.ic_empty_star
                }
        )
        star3.setImageResource(
                when (movie.rating) {
                    3,4,5 -> R.drawable.ic_star
                    else -> R.drawable.ic_empty_star
                }
        )
        star4.setImageResource(
                when (movie.rating) {
                    4,5 -> R.drawable.ic_star
                    else -> R.drawable.ic_empty_star
                }
        )
        star5.setImageResource(
                when (movie.rating) {
                    5 -> R.drawable.ic_star
                    else -> R.drawable.ic_empty_star
                }
        )

        like.setImageResource(if (movie.isLiked) R.drawable.ic_like else R.drawable.ic_empty_like)

        moviePoster.setImageResource(movie.poster)

    }

}

interface OnRecyclerItemClicked {
    fun onClick(movie: Movie)
}