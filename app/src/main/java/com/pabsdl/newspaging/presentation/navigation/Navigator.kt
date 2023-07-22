package com.pabsdl.newspaging.presentation.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.pabsdl.newspaging.R
import com.pabsdl.newspaging.presentation.beerlist.view.BeerListFragment

class Navigator {

    var activity: FragmentActivity? = null

    fun showBeerList() {
        activity?.run {
            supportFragmentManager.commit {
                add(R.id.content_view, BeerListFragment())
            }
        }
    }
}