package com.pabsdl.newspaging.presentation.newslist.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pabsdl.newspaging.data.model.NewsItem
import com.pabsdl.newspaging.databinding.NewsItemBinding

class NewsListAdapter: PagingDataAdapter<NewsItem, NewsListAdapter.ViewHolder>(DataDifferentiator) {

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

        fun bind(item: NewsItem) {
            binding.apply {
                titleTextview.text = item.title
            }
        }
    }

    object DataDifferentiator : DiffUtil.ItemCallback<NewsItem>() {

        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem == newItem
        }
    }

}