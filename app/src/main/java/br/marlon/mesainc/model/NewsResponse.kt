package br.marlon.mesainc.model

data class News(val data: List<NewsItem>)

data class NewsItem(val title: String, val description: String, val content: String, val image_url: String,
                    val author: String, val url: String, val published_at: String)