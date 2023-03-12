package com.example.flow.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flow.data.log.model.SearchLog
import com.example.flow.data.movie.model.Movie
import com.example.flow.databinding.ItemLogBinding


class SearchLogAdapter
    : RecyclerView.Adapter<SearchLogAdapter.SearchLogViewHolder>() {

    private var logList = emptyList<SearchLog>()
    private lateinit var listener: OnItemClickListener

    class SearchLogViewHolder(val binding: ItemLogBinding) : RecyclerView.ViewHolder(binding.root)

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchLogViewHolder {
        val binding = ItemLogBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchLogViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: SearchLogViewHolder, position: Int) {
        holder.binding.apply {
            logTextView.text = logList[position].searchLog
        }
        holder.itemView.setOnClickListener {
            listener.onClick(it, position)
        }
    }

    // 뷰 홀더의 개수 리턴
    override fun getItemCount(): Int {
        return logList.size
    }

    // 데이터 변경시 리스트 다시 할당
    fun setData(newList : List<SearchLog>){
        logList = newList
        // 새로고침
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }


}