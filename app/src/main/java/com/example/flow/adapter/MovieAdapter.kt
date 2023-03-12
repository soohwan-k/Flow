package com.example.flow.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flow.data.model.Movie
import com.example.flow.databinding.ItemMovieBinding


class MovieAdapter
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList = emptyList<Movie>()
    private lateinit var listener: OnItemClickListener

    class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
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

    // 뷰 홀더의 개수 리턴
    override fun getItemCount(): Int {
        return movieList.size
    }

    // 데이터 변경시 리스트 다시 할당
    fun setData(newList : List<Movie>){
        movieList = newList
        // 새로고침
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }


//    private fun removeTag(s :  String): String {
//        var a = s.replace("<b>", "")
//        a = a.replace("</b>", "")
//        a = a.replace("&amp;", "&")
//        return a
//    }

    fun removeTag(html: String?): String? {
        return Html.fromHtml(html).toString()
    }

}