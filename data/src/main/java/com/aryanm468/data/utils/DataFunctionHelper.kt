package com.aryanm468.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

internal object DataFunctionHelper {

    /**
     * Checks if the network is available and connected.
     *
     * @param context The context used to get the ConnectivityManager.
     * @return True if the network is available, false otherwise.
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkCapabilities = connectivityManager?.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}