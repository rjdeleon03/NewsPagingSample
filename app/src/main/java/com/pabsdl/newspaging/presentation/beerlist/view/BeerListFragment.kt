package com.pabsdl.newspaging.presentation.beerlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pabsdl.newspaging.databinding.FragmentBeerListBinding
import com.pabsdl.newspaging.presentation.beerlist.viewmodel.BeerListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BeerListFragment : Fragment() {

    private val viewModel: BeerListViewModel by viewModels()

    private lateinit var binding: FragmentBeerListBinding
    private val adapter: BeerListAdapter by lazy { BeerListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBeerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                adapter = this@BeerListFragment.adapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}