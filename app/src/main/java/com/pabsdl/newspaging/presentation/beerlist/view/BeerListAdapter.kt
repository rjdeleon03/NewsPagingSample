package com.pabsdl.newspaging.presentation.beerlist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pabsdl.domain.model.BeerItem
import com.pabsdl.newspaging.databinding.NewsItemBinding

class BeerListAdapter: PagingDataAdapter<BeerItem, BeerListAdapter.ViewHolder>(DataDifferentiator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class ViewHolder(private val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(item: com.pabsdl.domain.model.BeerItem) {
            binding.apply {
                titleTextview.text = item.name
            }
        }
    }

    object DataDifferentiator : DiffUtil.ItemCallback<com.pabsdl.domain.model.BeerItem>() {

        override fun areItemsTheSame(oldItem: com.pabsdl.domain.model.BeerItem, newItem: com.pabsdl.domain.model.BeerItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: com.pabsdl.domain.model.BeerItem, newItem: com.pabsdl.domain.model.BeerItem): Boolean {
            return oldItem == newItem
        }
    }

}