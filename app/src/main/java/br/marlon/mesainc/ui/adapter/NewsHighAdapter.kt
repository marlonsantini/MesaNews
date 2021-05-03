package br.marlon.mesainc.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import br.marlon.mesainc.R
import br.marlon.mesainc.databinding.RowHighBinding
import br.marlon.mesainc.model.NewsItem
import br.marlon.mesainc.ui.activity.NoticiaActivity
import coil.load

class NewsHighAdapter(val context: Context, private val destaque: List<NewsItem>) : RecyclerView.Adapter<NewsHighAdapter.NewsHighViewHolder>() {



    class NewsHighViewHolder(val binding: RowHighBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHighViewHolder {
        val binding = RowHighBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsHighViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHighViewHolder, position: Int) {
        with(holder) {
            with(destaque[position]) {
                binding.titleTxt.text = title
                val typeface = ResourcesCompat.getFont(context, R.font.walbaumbold)
                binding.titleTxt.typeface = typeface
                //binding.descTxt.text = content
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

    override fun getItemCount() = destaque.size

}