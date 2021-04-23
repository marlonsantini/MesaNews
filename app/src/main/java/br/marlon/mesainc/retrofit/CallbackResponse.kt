package br.marlon.mesainc.retrofit

interface CallbackResponse<T> {
    fun success(response: T)
}