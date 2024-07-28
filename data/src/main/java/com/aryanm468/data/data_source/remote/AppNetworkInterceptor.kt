package com.aryanm468.data.data_source.remote

import android.content.Context
import com.aryanm468.data.utils.DataFunctionHelper
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AppNetworkInterceptor @Inject constructor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (!DataFunctionHelper.isNetworkAvailable(context)) {
            throw NoInternetConnectionException()
        }
        return chain.proceed(request)
    }
}