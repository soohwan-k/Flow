package com.example.flow

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flow.Constants.Companion.CLIENT_ID
import com.example.flow.Constants.Companion.CLIENT_PW
import com.example.flow.adapter.MovieAdapter
import com.example.flow.data.model.Movie
import com.example.flow.data.repository.MovieRepository
import com.example.flow.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(){


    private lateinit var viewModel : MainViewModel
    private lateinit var binding : ActivityMainBinding
    private val movieAdapter by lazy { MovieAdapter() }
    private var page = 1
    private var totalNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 뷰바인딩
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 어댑터 연결
        binding.searchRecyclerView.adapter = movieAdapter
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        initViewModel()

        binding.searchButton.setOnClickListener{
            var keyword: String = binding.searchEditText.text.toString()
            search(keyword)
        }
    }


    private fun initViewModel() {
        //repository 와 뷰모델 연결
        val repository = MovieRepository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private fun search(keyword: String) {
        viewModel.getMovieResponse(CLIENT_ID, CLIENT_PW, keyword)
        viewModel.movieResponse.observe(this) {
            //responseList.clear()
            var responseList = (it.body()?.items ?: arrayListOf<Movie>()) as ArrayList<Movie>

            //pagingList += responseList
            if (it.isSuccessful) {
                Log.d(TAG, "search success")
                movieAdapter.setData(responseList)

            } else {
                Log.d(TAG, "search: ${it.errorBody()?.string()!!}")

            }
        }
    }

//    private fun paging() {
//        binding.searchRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                val recyclerViewPosition =
//                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
//                val totalCount = recyclerView.adapter?.itemCount?.minus(1)
//
//                if (recyclerViewPosition == totalCount) {
//                    page++
//                    search(searchWord, page)
//                }
//            }
//        })
//    }

}