package com.pabsdl.newspaging.presentation.old.beerdetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.pabsdl.newspaging.R
import com.pabsdl.newspaging.databinding.FragmentBeerDetailBinding
import com.pabsdl.newspaging.presentation.old.beerdetail.viewmodel.BeerDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beer_detail, container,false)
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
                            binding.beer = beerItem
                        }
                    }
            }
        }

        arguments?.getInt(BEER_ID_KEY)?.let { beerId ->
            viewModel.getBeerDetail(beerId)
        }
    }
}


@BindingAdapter("android:customImage")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(this)
        .load(url)
        .error(R.drawable.ic_launcher_background)
        .into(this)
}