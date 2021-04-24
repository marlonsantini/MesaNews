package br.marlon.mesainc.retrofit.service

import br.marlon.mesainc.model.LoginResponse
import br.marlon.mesainc.model.DefaultResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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
            @Field("email") email: String,
            @Field("password") password: String
    ) : Call<LoginResponse>
}