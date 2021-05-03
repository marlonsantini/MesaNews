package br.marlon.mesainc.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.marlon.mesainc.databinding.ActivityNoticiaBinding
import coil.load


class NoticiaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoticiaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticiaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val title = intent.getStringExtra("title")
        val autor = intent.getStringExtra("autor")
        val image = intent.getStringExtra("image")
        val content = intent.getStringExtra("content")
        val url = intent.getStringExtra("url")

        binding.tvTitulo.text = title
        binding.tvAutor.text = autor
        binding.imgNews.load(image)
        binding.tvContent.text = content
        binding.imgShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, url)
            startActivity(Intent.createChooser(shareIntent, "Compartilhar link usando"))
        }


    }
}