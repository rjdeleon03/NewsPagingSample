package com.pabsdl.newspaging.presentation.newslist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pabsdl.newspaging.data.model.NewsItemResponse
import com.pabsdl.newspaging.data.network.NetworkResult
import com.pabsdl.newspaging.data.network.RetrofitInstance
import com.pabsdl.newspaging.databinding.ActivityNewsListBinding
import com.pabsdl.newspaging.presentation.newslist.repository.NewsListRepository
import com.pabsdl.newspaging.presentation.newslist.viewmodel.NewsListViewModel
import com.pabsdl.newspaging.presentation.newslist.viewmodel.NewsListViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsListActivity : AppCompatActivity() {

    lateinit var viewModel: NewsListViewModel

    private lateinit var binding: ActivityNewsListBinding
    private val adapter: NewsListAdapter by lazy { NewsListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initViewModel()
    }

    private fun initViewModel() {
        val activity = this
        val newsService = RetrofitInstance
            .getRetrofitInstance()
        val repository = NewsListRepository(newsService)
        val viewModelFactory = NewsListViewModelFactory(repository)
        viewModel = ViewModelProvider(activity, viewModelFactory)[NewsListViewModel::class.java].apply {
            lifecycleScope.launch {
                newsListData.collect {
                    adapter.submitData(it)
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            newsListRecyclerview.apply {
                adapter = this@NewsListActivity.adapter
                layoutManager = LinearLayoutManager(this@NewsListActivity)
            }
        }
    }
}