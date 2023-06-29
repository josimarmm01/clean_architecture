package com.example.josimar.presentetion.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Movie
import com.example.josimar.R
import com.example.josimar.databinding.ItemMovieBinding
import com.example.josimar.framework.imageloader.ImageLoad
import com.example.josimar.util.OnItemClick

class MoviesViewHolder(
    itemMovieBinding: ItemMovieBinding,
    private val imageLoad: ImageLoad,
    private val onItemClick: OnItemClick
) : RecyclerView.ViewHolder(itemMovieBinding.root) {

        private val textName = itemMovieBinding.textViewMovieName
        private val imageMovie = itemMovieBinding.imageViewMovie

    fun bind(movie: Movie) {

        textName.text = movie.name
        imageLoad.load(imageMovie, movie.igmUrl, R.drawable.ic_launcher_background)

        itemView.setOnClickListener {
            onItemClick.invoke(movie.idMovie)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            imageLoad: ImageLoad,
            onItemClick: OnItemClick
        ): MoviesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemMovieBinding.inflate(inflater, parent, false)
            return MoviesViewHolder(itemBinding, imageLoad, onItemClick)
        }
    }

}
