package br.marlon.mesainc.ui.activity

import android.R
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import br.marlon.mesainc.databinding.ActivityFeedBinding
import br.marlon.mesainc.model.News
import br.marlon.mesainc.retrofit.RetrofitInitializer
import br.marlon.mesainc.ui.adapter.NewsAdapter
import br.marlon.mesainc.ui.adapter.NewsHighAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getNews()
    }

    //FUNÇÃO DE CHAMADA DA API E CARREGAMENTO DAS LISTAS
    private fun getNews() {
        RetrofitInitializer.instance.getNews()
            .enqueue(object : Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    //val news = response.body()!!.data
                    if (response.isSuccessful) {
                        //d("marlon", "onResponde:  ${response.body()!!.data}")

                        //LISTA NOTICIAS
                        binding.rvNews.adapter.apply {
                            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
                                this@FeedActivity,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                            binding.rvNews.layoutManager = layoutManager
                            binding.rvNews.adapter =
                                NewsAdapter(this@FeedActivity, response.body()!!.data)
                        }

                        // LISTA NOTICIAS DESTAQUE
                        binding.vpNewsHigh.adapter.apply {
                            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
                                this@FeedActivity,
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            binding.vpNewsHigh.layoutManager = layoutManager
                            binding.vpNewsHigh.adapter =
                                NewsHighAdapter(this@FeedActivity, response.body()!!.data)
                            val pagerSnapHelper = PagerSnapHelper()
                            pagerSnapHelper.attachToRecyclerView(binding.vpNewsHigh)
                            binding.indicator.attachToRecyclerView(
                                binding.vpNewsHigh,
                                pagerSnapHelper
                            )
                        }
                        Toast.makeText(
                            applicationContext,
                            response.message(),
                            Toast.LENGTH_LONG
                        ).show()


                    } else {
                        Toast.makeText(
                            applicationContext,
                            response.message(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }
            })
    }
}