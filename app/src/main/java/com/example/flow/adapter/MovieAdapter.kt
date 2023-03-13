package com.example.flow.adapter

import android.annotation.SuppressLint
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flow.data.movie.model.Movie
import com.example.flow.databinding.ItemMovieBinding


class MovieAdapter
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList = emptyList<Movie>()
    private lateinit var listener: OnItemClickListener

    class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.apply {
            titleTextView.text = removeTag(movieList[position].title)
            yearTextView.text = movieList[position].pubDate
            scoreTextView.text = movieList[position].userRating.toString()
            Glide.with(posterImageView.context)
                .load(movieList[position].image)
                .into(posterImageView)
        }
        holder.itemView.setOnClickListener {
            listener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<Movie>) {
        movieList = newList
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    private fun removeTag(html: String): String {
        return Html.fromHtml(html).toString()
    }

}
