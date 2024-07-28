package com.aryanm468.data.data_source.remote

import java.io.IOException

class NoInternetConnectionException : IOException() {
    override val message: String
        get() = "No internet connection"
}