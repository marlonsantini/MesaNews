package br.marlon.mesainc.retrofit.service

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserClient {

    @FormUrlEncoded
    @POST("/v1/client/auth/signup")
    fun login(
            @Field("email") email: String,
            @Field("password") password: String
    ) : Any
}