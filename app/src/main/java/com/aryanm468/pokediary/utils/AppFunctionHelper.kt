package com.aryanm468.pokediary.utils

import android.net.Uri

object AppFunctionHelper {

    fun getQueryParam(url: String, param: String): String? {
        return if (url.isNotBlank()) Uri.parse(url).getQueryParameter(param) else ""
    }

    fun getIdFromUrl(url: String): String? {
        return url.split("/").lastOrNull { it.isNotEmpty() && it.all { char -> char.isDigit() } }
    }

    fun addLeadingZeroes(input: String): String {
        return if (input.length >= 3) {
            input
        } else {
            input.padStart(3, '0')
        }
    }

}