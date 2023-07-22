package com.pabsdl.newspaging.presentation.beerlist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pabsdl.newspaging.databinding.ActivityBeerListBinding
import com.pabsdl.newspaging.presentation.beerlist.viewmodel.BeerListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BeerListActivity : AppCompatActivity() {

    private val viewModel: BeerListViewModel by viewModels()

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
        viewModel.apply {
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