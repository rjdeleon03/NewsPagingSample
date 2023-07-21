package com.pabsdl.newspaging.presentation.beerlist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pabsdl.newspaging.data.database.DatabaseInstance
import com.pabsdl.newspaging.data.network.RetrofitInstance
import com.pabsdl.newspaging.databinding.ActivityBeerListBinding
import com.pabsdl.newspaging.presentation.beerlist.repository.BeerListRepository
import com.pabsdl.newspaging.presentation.beerlist.viewmodel.BeerListViewModel
import com.pabsdl.newspaging.presentation.beerlist.viewmodel.BeerListViewModelFactory
import kotlinx.coroutines.launch

class BeerListActivity : AppCompatActivity() {

    lateinit var viewModel: BeerListViewModel

    private lateinit var binding: ActivityBeerListBinding
    private val adapter: BeerListAdapter by lazy { BeerListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initViewModel()
    }

    private fun initViewModel() {
        val activity = this
        val beerDatabase = DatabaseInstance
            .getDatabaseInstance(this)
        val beerService = RetrofitInstance
            .getRetrofitInstance()
        val repository = BeerListRepository(beerDatabase, beerService)
        val viewModelFactory = BeerListViewModelFactory(repository)
        viewModel = ViewModelProvider(activity, viewModelFactory)[BeerListViewModel::class.java].apply {
            lifecycleScope.launch {
                beerListData.collect {
                    adapter.submitData(it)
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            beerListRecyclerView.apply {
                adapter = this@BeerListActivity.adapter
                layoutManager = LinearLayoutManager(this@BeerListActivity)
            }
        }
    }
}