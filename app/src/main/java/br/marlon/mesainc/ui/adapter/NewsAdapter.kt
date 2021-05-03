package br.marlon.mesainc.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import br.marlon.mesainc.databinding.RowNewsModBinding
import br.marlon.mesainc.model.NewsItem
import br.marlon.mesainc.ui.activity.NoticiaActivity
import coil.load
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class NewsAdapter(val context: Context, private val artigos: List<NewsItem>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: RowNewsModBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = RowNewsModBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        with(holder) {
            with(artigos[position]) {
                binding.titleTxt.text = title
                binding.autorTxt.text = author
                binding.imgCard.load(image_url)

                binding.news.setOnClickListener {
                    val intent = Intent(context, NoticiaActivity::class.java)
                        intent.putExtra("title", title)
                        intent.putExtra("autor", author)
                        intent.putExtra("image", image_url)
                        intent.putExtra("content", content)
                        intent.putExtra("url", url)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount() = artigos.size


}



