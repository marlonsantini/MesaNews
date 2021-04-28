package br.marlon.mesainc.ui.activity

import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.marlon.mesainc.databinding.ActivityFeedBinding
import br.marlon.mesainc.model.NewsItem
import br.marlon.mesainc.model.News
import br.marlon.mesainc.retrofit.RetrofitInitializer
import br.marlon.mesainc.ui.adapter.NewsAdapter
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

    private fun getNews() {
        RetrofitInitializer.instance.getNews()
                .enqueue(object : Callback<News> {
                    override fun onResponse(call: Call<News>, response: Response<News>) {
                        //val news = response.body()!!.data
                        if (response.isSuccessful) {
                            d("marlon", "onResponde:  ${response.body()!!.data}")

                            binding.rvNews.adapter.apply {
                                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@FeedActivity, LinearLayoutManager.VERTICAL, false)
                                binding.rvNews.setLayoutManager(layoutManager)
                                binding.rvNews.adapter = NewsAdapter(response.body()!!.data)
                            }

                            binding.vpNewsHigh.adapter.apply {
                                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@FeedActivity, LinearLayoutManager.HORIZONTAL, false)
                                binding.vpNewsHigh.setLayoutManager(layoutManager)
                                binding.vpNewsHigh.adapter = NewsAdapter(response.body()!!.data)
                            }
                            Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()


                        } else {
                            Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<News>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })
    }
}
//    private fun configureList(news: News) {
//        binding.rvNews.adapter.apply {
//            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@FeedActivity)
//            binding.rvNews.setLayoutManager(layoutManager)
//            //binding.rvNewsHigh.adapter = NewsAdapter(response.body()!!.data)
//        }
//    }
