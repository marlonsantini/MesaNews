package br.marlon.mesainc.retrofit.client

import br.marlon.mesainc.model.*
import retrofit2.Call
import retrofit2.http.*

interface UserClient {

    @FormUrlEncoded
    @POST("auth/signup")
    fun cadastro(
            @Field("nome") nome: String,
            @Field("email") email: String,
            @Field("password") password: String
    ) : Call<DefaultResponse>

    @FormUrlEncoded
    @POST("auth/signin")
    fun login(
//            @Header("") token: String,
            @Field("email") email: String,
            @Field("password") password: String
    ) : Call<LoginResponse>

    @GET("news/highlights")
    fun getNewsHigh(): Call<News>

    @GET("news?current_page=&per_page=&published_at=")
    fun getNews(): Call<News>
}