package br.marlon.mesainc.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.marlon.mesainc.databinding.RowNewsBinding
import br.marlon.mesainc.model.NewsItem
import coil.load


class NewsAdapter( private val artigos: List<NewsItem>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: RowNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = RowNewsBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        with(holder) {
            with(artigos[position]) {
                binding.titleTxt.text = title
                binding.descTxt.text = content
                binding.imgCard.load(image_url)
            }
        }
    }

    override fun getItemCount() = artigos.size


}



