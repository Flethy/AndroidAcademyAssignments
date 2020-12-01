package ru.flethy.androidacademyassignments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentManager

class FragmentMoviesList : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        view?.findViewById<ImageView>(R.id.poster_background)
                ?.setOnClickListener {
                        fragmentManager?.let {
                            it.beginTransaction()
                                .addToBackStack(null)
                                .add(R.id.container, FragmentMoviesDetails())
                                .commit()
                        }
                }

        return view
    }

}