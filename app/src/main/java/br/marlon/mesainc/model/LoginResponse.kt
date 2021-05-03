package br.marlon.mesainc.model

data class LoginResponse(val user: User, val token: String, val code: String)