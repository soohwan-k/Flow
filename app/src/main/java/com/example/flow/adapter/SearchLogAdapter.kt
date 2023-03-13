package com.example.flow.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flow.data.log.model.SearchLog
import com.example.flow.databinding.ItemLogBinding


class SearchLogAdapter
    : RecyclerView.Adapter<SearchLogAdapter.SearchLogViewHolder>() {

    private var logList = emptyList<SearchLog>()
    private lateinit var listener: OnItemClickListener

    class SearchLogViewHolder(val binding: ItemLogBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchLogViewHolder {
        val binding = ItemLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchLogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchLogViewHolder, position: Int) {
        holder.binding.apply {
            logTextView.text = logList[position].searchLog
        }
        holder.itemView.setOnClickListener {
            listener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return logList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<SearchLog>) {
        logList = newList
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}
