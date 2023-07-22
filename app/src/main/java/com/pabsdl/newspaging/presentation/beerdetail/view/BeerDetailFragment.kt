package com.pabsdl.newspaging.presentation.beerdetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pabsdl.newspaging.databinding.FragmentBeerDetailBinding

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

    private lateinit var binding: FragmentBeerDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBeerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}