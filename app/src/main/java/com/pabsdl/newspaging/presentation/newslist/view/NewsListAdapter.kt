package com.pabsdl.newspaging.presentation.newslist.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pabsdl.newspaging.data.NewsItem
import com.pabsdl.newspaging.databinding.NewsItemBinding

class NewsListAdapter: RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val newsList = arrayListOf<NewsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = newsList[position]
        holder.bind(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewsList(list: ArrayList<NewsItem>) {
        newsList.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
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
}