package com.example.flow.presentation.log

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flow.R
import com.example.flow.adapter.MovieAdapter
import com.example.flow.adapter.OnItemClickListener
import com.example.flow.adapter.SearchLogAdapter
import com.example.flow.data.log.model.SearchLog
import com.example.flow.databinding.ActivityLogBinding
import com.example.flow.databinding.ActivityMainBinding

class LogActivity : AppCompatActivity() {
    private val searchLogViewModel : SearchLogViewModel by viewModels{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                SearchLogViewModel(application) as T
        }
    }
    //private val searchLogs = arrayListOf<SearchLog>();
    private val searchLogAdapter by lazy { SearchLogAdapter() }
    private lateinit var binding: ActivityLogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logRecyclerView.adapter = searchLogAdapter
        binding.logRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        initView()

    }

    private fun initView(){
        searchLogViewModel.getAll().observe(this) { searchLogs ->
            val newList = arrayListOf<SearchLog>()
            var s = searchLogs.size
            if (s>10) {
                for (i in s-10 until s) {
                    newList.add(searchLogs[i])
                }
                searchLogAdapter.setData(newList)
            }else{
                searchLogAdapter.setData(searchLogs)
            }

        }
    }
//
//    private fun searchLogClick() {
//        searchLogAdapter.setItemClickListener(object : OnItemClickListener {
//            override fun onClick(v: View, position: Int) {
//                var intent = Intent(Intent.ACTION_VIEW, Uri.parse(responseList[position].link))
//                startActivity(intent)
//            }
//        })
//    }
}