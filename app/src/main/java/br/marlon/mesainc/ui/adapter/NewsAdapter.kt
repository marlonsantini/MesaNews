package br.marlon.mesainc.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.marlon.mesainc.R
import br.marlon.mesainc.databinding.RowItemBinding
import br.marlon.mesainc.model.NewsItem
import br.marlon.mesainc.model.NewsResponse


class NewsAdapter(

        private val newsData: List<NewsResponse>
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = RowItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        with(holder) {
            with(newsData[position]) {
                binding.titleTxt.text
                binding.descTxt.text
            }
        }
    }

    override fun getItemCount() = newsData.size

    inner class NewsViewHolder(val binding: RowItemBinding)
        : RecyclerView.ViewHolder(binding.root)
}



