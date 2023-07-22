package com.pabsdl.newspaging.presentation.beerlist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pabsdl.domain.model.BeerItem
import com.pabsdl.newspaging.databinding.NewsItemBinding

class BeerListAdapter(
    private val onClickListener: (Int) -> Unit
): PagingDataAdapter<BeerItem, BeerListAdapter.ViewHolder>(DataDifferentiator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater)
        return ViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class ViewHolder(private val binding: NewsItemBinding,
                     private val onClickListener: (Int) -> Unit):
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.apply {
                setOnClickListener {
                    (tag as? BeerItem)?.let { beerItem ->
                        onClickListener.invoke(beerItem.id)
                    }
                }
            }
        }

        fun bind(item: BeerItem) {
            binding.apply {
                titleTextview.text = item.name
                root.tag = item
            }
        }
    }

    object DataDifferentiator : DiffUtil.ItemCallback<BeerItem>() {

        override fun areItemsTheSame(oldItem: BeerItem, newItem: BeerItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BeerItem, newItem: BeerItem): Boolean {
            return oldItem == newItem
        }
    }

}