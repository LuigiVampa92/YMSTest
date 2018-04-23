package com.luigivampa92.yandextest.data

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    private var token = ""
    private val tokenType = "OAuth"

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val header = String.format("%s %s", tokenType, token)
        request = request.newBuilder()
                .addHeader("Authorization", header)
                .build()

        return chain.proceed(request)
    }

    fun setToken(token: String) {
        this.token = token
    }
}