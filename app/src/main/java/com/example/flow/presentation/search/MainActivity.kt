package com.example.flow.presentation.search

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flow.Constants.Companion.CLIENT_ID
import com.example.flow.Constants.Companion.CLIENT_PW
import com.example.flow.adapter.MovieAdapter
import com.example.flow.adapter.OnItemClickListener
import com.example.flow.data.movie.model.Movie
import com.example.flow.data.log.model.SearchLog
import com.example.flow.data.movie.repository.MovieRepository
import com.example.flow.databinding.ActivityMainBinding
import com.example.flow.presentation.log.LogActivity
import com.example.flow.presentation.log.SearchLogViewModel


class MainActivity : AppCompatActivity() {

    private val searchLogViewModel: SearchLogViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                SearchLogViewModel(application) as T
        }
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val movieAdapter by lazy { MovieAdapter() }
    private var responseList = arrayListOf<Movie>()
    private var pagingList = arrayListOf<Movie>()
    private var page = 1
    private lateinit var searchWord: String
    private var total = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchRecyclerView.adapter = movieAdapter
        binding.searchRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        initMainViewModel()
        logButtonClick()
        getLogIntent()
        searchButtonClick()
        paging()
        listClick()
    }


    private fun searchButtonClick() {
        binding.searchButton.setOnClickListener {
            pagingList.clear()
            page = 1
            searchWord = binding.searchEditText.text.toString()
            if (searchWord.isNotEmpty()) {
                searchLogViewModel.insert(SearchLog(searchWord))
            }

            search(page, searchWord)
        }
    }

    private fun logButtonClick() {
        binding.logButton.setOnClickListener {
            val intent = Intent(this, LogActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initMainViewModel() {
        val repository = MovieRepository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private fun search(start: Int, keyword: String) {
        viewModel.getMovieResponse(CLIENT_ID, CLIENT_PW, start, keyword)
        viewModel.movieResponse.observe(this) {
            responseList.clear()
            responseList = (it.body()?.items ?: arrayListOf<Movie>()) as ArrayList<Movie>
            total = it.body()?.total ?: 0
            pagingList += responseList
            if (it.isSuccessful) {
                movieAdapter.setData(pagingList.distinct())

            } else {
                Log.d(TAG, "search: ${it.errorBody()?.string()!!}")

            }
        }
    }

    private fun listClick() {
        movieAdapter.setItemClickListener(object : OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val intent = Intent(ACTION_VIEW, Uri.parse(pagingList[position].link))
                startActivity(intent)
            }
        })
    }

    private fun getLogIntent() {
        if (intent.getStringExtra("searchWord") != null) {
            pagingList.clear()
            searchWord = intent.getStringExtra("searchWord")!!
            page = 1
            search(page, searchWord)
        }
    }

    private fun paging() {
        binding.searchRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val recyclerViewPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val totalCount = recyclerView.adapter?.itemCount?.minus(1)

                if (recyclerViewPosition == totalCount && pagingList.size < total) {
                    page += 10
                    search(page, searchWord)
                }
            }
        })
    }

}
