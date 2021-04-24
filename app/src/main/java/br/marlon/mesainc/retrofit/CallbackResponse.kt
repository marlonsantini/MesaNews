package br.marlon.mesainc.retrofit

interface CallbackResponse<T> {
    fun success(response: T)
    fun failure(
            code: String,
            field: String,
            message: String
    )
}