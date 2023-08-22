package com.pabsdl.newspaging.presentation.old.beerlist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.pabsdl.newspaging.databinding.NewsItemBinding
import com.pabsdl.newspaging.databinding.NewsItemSeparatorBinding

class BeerListAdapter(
    private val onClickListener: (Int) -> Unit
): PagingDataAdapter<BeerListViewItem, RecyclerView.ViewHolder>(DataDifferentiator) {

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_SEPARATOR = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<*> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val binding = NewsItemBinding.inflate(inflater)
                ItemViewHolder(binding, onClickListener)
            }
            else -> {
                val binding = NewsItemSeparatorBinding.inflate(inflater)
                SeparatorViewHolder(binding, onClickListener)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            when (holder) {
                is ItemViewHolder -> {
                    holder.bind(it)
                }
                is SeparatorViewHolder -> {
                    holder.bind(it)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is BeerListViewItem.Item -> {
                VIEW_TYPE_ITEM
            }
            else -> VIEW_TYPE_SEPARATOR
        }

    }

    class ItemViewHolder(binding: NewsItemBinding,
                         onClickListener: (Int) -> Unit
    ): ViewHolder<NewsItemBinding>(binding, onClickListener) {

        init {
            binding.root.apply {
                setOnClickListener {
                    (tag as? BeerListViewItem.Item)?.let { item ->
                        onClickListener.invoke(item.data.id)
                    }
                }
            }
        }

        override fun bind(item: BeerListViewItem) {
            binding.apply {
                (item as? BeerListViewItem.Item)?.let {
                    titleTextview.text = it.data.name
                    root.tag = item
                }
            }
        }
    }

    class SeparatorViewHolder(
        binding: NewsItemSeparatorBinding,
        onClickListener: (Int) -> Unit
    ): ViewHolder<NewsItemSeparatorBinding>(binding, onClickListener) {
        override fun bind(item: BeerListViewItem) {
            binding.apply {
                (item as? BeerListViewItem.Separator)?.let {
                    titleTextview.text = it.label
                }
            }
        }

    }

    abstract class ViewHolder<B: ViewBinding>(
        protected val binding: B,
        private val onClickListener: (Int) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {

        abstract fun bind(item: BeerListViewItem)
    }

    object DataDifferentiator : DiffUtil.ItemCallback<BeerListViewItem>() {

        override fun areItemsTheSame(oldItem: BeerListViewItem, newItem: BeerListViewItem): Boolean {
            return if (oldItem is BeerListViewItem.Item && newItem is BeerListViewItem.Item) {
                oldItem.data.id == newItem.data.id
            } else if (oldItem is BeerListViewItem.Separator && newItem is BeerListViewItem.Separator) {
                oldItem.label == newItem.label
            } else {
                false
            }
        }

        override fun areContentsTheSame(oldItem: BeerListViewItem, newItem: BeerListViewItem): Boolean {
            return oldItem == newItem
        }
    }

}