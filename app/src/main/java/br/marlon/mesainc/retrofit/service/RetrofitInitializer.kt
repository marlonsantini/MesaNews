package br.marlon.mesainc.retrofit.service

import br.marlon.mesainc.retrofit.client.UserClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInitializer {

    private const val BASE_URL = "https://mesa-news-api.herokuapp.com/v1/client/"

    //private val AUTH = "Basic "+ Base64.encodeToString("marlon:123456".toByteArray(), Base64.NO_WRAP)
    private val AUTH = "Berear eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MjU5LCJlbWFpbCI6ImRpbWFzLmdhYnJpZWxAenJvYmFuay5jb20uYnIifQ.a3j7sRx8FIedZCfDGLocduOYpcibfIenX7TVJjv6Sis"


    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()

                val requestBuilder = original.newBuilder()
                        .addHeader("Authorization", AUTH)
                        .method(original.method, original.body)

                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()

    val instance: UserClient by lazy{
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        retrofit.create(UserClient::class.java)
    }
}