package com.pabsdl.newspaging.presentation.beerdetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.pabsdl.newspaging.databinding.FragmentBeerDetailBinding
import com.pabsdl.newspaging.presentation.beerdetail.viewmodel.BeerDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BeerDetailFragment : Fragment() {

    companion object {

        const val TAG = "BeerDetailFragment"
        private const val BEER_ID_KEY = "BEER_ID"

        @JvmStatic
        fun newInstance(beerId: Int): BeerDetailFragment {
            val bundle = Bundle().apply {
                putInt(BEER_ID_KEY, beerId)
            }
            return BeerDetailFragment().apply {
                arguments = bundle
            }
        }
    }

    private val viewModel: BeerDetailViewModel by viewModels()

    private lateinit var binding: FragmentBeerDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBeerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.apply {
                selectedBeer
                    .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                    .collect { beerItem ->
                        beerItem?.let {
                            binding.apply {
                                beerNameTextview.text = it.name
                            }
                        }
                    }
            }
        }

        arguments?.getInt(BEER_ID_KEY)?.let { beerId ->
            viewModel.getBeerDetail(beerId)
        }
    }
}