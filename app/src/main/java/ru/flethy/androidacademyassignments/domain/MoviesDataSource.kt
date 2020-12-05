package ru.flethy.androidacademyassignments.domain

import ru.flethy.androidacademyassignments.R
import ru.flethy.androidacademyassignments.model.Movie

class MoviesDataSource {
    fun getMovies(): List<Movie> {
        return listOf(
                Movie(1,
                        "Avengers: End Game",
                        13, "Action, Adventure, Drama",
                        125,
                        137,
                        4,
                        R.drawable.poster_avengers_end_game,
                        R.drawable.avengers_poster,
                        "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
                        ActorsDataSource().getActors(),
                        false),
                Movie(2,
                        "Tenet",
                        16,
                        "Action, Sci-Fi, Thriller",
                        98,
                        97,
                        5,
                        R.drawable.poster_tenet,
                        null,
                        null,
                        null,
                        true),
                Movie(3,
                        "Black Widow",
                        13,
                        "Action, Adventure, Sci-Fi",
                        38,
                        102,
                        4,
                        R.drawable.poster_black_widow,
                        null,
                        null,
                        null,
                        false),
                Movie(4,
                        "Wonder Woman 1984",
                        13,
                        "Action, Adventure, Fantasy",
                        74,
                        120,
                        5,
                        R.drawable.poster_wonder_woman_1984,
                        null,
                        null,
                        null,
                        false)
        )
    }
}