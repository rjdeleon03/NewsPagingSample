package com.pabsdl.newspaging.presentation.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.pabsdl.newspaging.R
import com.pabsdl.newspaging.presentation.beerdetail.view.BeerDetailFragment
import com.pabsdl.newspaging.presentation.beerlist.view.BeerListFragment

class Navigator {

    var activity: FragmentActivity? = null

    fun showBeerList() {
        activity?.run {
            supportFragmentManager.commit {
                replace(R.id.content_view, BeerListFragment())
            }
        }
    }

    fun showBeerDetail(beerId: Int) {
        activity?.run {
            supportFragmentManager.commit {
                replace(R.id.content_view, BeerDetailFragment.newInstance(beerId))
                addToBackStack(BeerDetailFragment.TAG)
            }
        }
    }
}