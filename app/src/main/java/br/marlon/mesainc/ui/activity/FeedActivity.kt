package br.marlon.mesainc.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.marlon.mesainc.databinding.ActivityFeedBinding
import br.marlon.mesainc.model.NewsItem
import br.marlon.mesainc.model.NewsResponse
import br.marlon.mesainc.retrofit.RetrofitInitializer
import br.marlon.mesainc.ui.adapter.NewsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedBinding
    private val news: MutableList<NewsResponse> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        RetrofitInitializer.instance.getNewHigh()
                .enqueue(object : Callback<NewsResponse> {
                    override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                        if (response.isSuccessful) {

                            configureList()
                            Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()


                        } else {
                            Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                })
    }

    private fun configureList() {
        binding.rvNewsHigh.adapter = NewsAdapter(news)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.rvNewsHigh.setLayoutManager(layoutManager);
    }
}