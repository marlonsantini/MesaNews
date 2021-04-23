package br.marlon.mesainc.retrofit.service

import retrofit2.http.POST

interface UserClient {

    @POST("login")
    fun login()

    @POST("cadastro")
    fun cadastro()
}