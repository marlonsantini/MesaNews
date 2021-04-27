package br.marlon.mesainc.model

data class NewsResponse(val data: List<NewsItem>)

data class NewsItem(val title: String, val content: String)