package com.srgnk.news.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.srgnk.news.data.GoogleNewsItem
import com.srgnk.news.databinding.NewsBinding

class NewsAdapter(private val listener: ItemClickListener) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun itemClicked(item: GoogleNewsItem)
    }

    private var values: ArrayList<GoogleNewsItem> = ArrayList()

    override fun getItemCount(): Int = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val itemBinding = NewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding, listener)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        holder.apply {
            Picasso.get().load(values[position].urlToImage).into(newsImage)
            title.text = values[position].title
            date.text = values[position].description
        }
    }

    fun setNews(values: List<GoogleNewsItem>) {
        this.values.addAll(values)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        binding: NewsBinding,
        private val listener: ItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        val newsImage = binding.newsImage
        val title = binding.name
        val date = binding.description

        init {
            binding.news.setOnClickListener {
                listener.itemClicked(values[adapterPosition])
            }
        }
    }
}