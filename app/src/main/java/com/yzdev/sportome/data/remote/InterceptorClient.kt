package com.yzdev.sportome.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class InterceptorClient(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("x-rapidapi-key", "142614ce2329c6a9134374f87a463046")
            .build()
        return chain.proceed(request)
    }

}