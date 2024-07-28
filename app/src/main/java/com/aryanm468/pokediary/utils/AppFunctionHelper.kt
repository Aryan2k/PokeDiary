package com.aryanm468.pokediary.utils

import android.net.Uri

object AppFunctionHelper {

    /**
     * Extracts the value of a specified query parameter from a given URL.
     *
     * @param url The URL from which to extract the query parameter value.
     * @param param The name of the query parameter to extract.
     * @return The value of the specified query parameter as a string, or an empty string if the URL is blank or the parameter is not found.
     */
    fun getQueryParam(url: String, param: String): String? {
        return if (url.isNotBlank()) Uri.parse(url).getQueryParameter(param) else ""
    }

    /**
     * Extracts the ID from a given URL. The ID is assumed to be the last non-empty segment of the URL that consists only of digits.
     *
     * @param url The URL from which to extract the ID.
     * @return The extracted ID as a string, or null if no valid ID is found.
     */
    fun getIdFromUrl(url: String): String? {
        return url.split("/").lastOrNull { it.isNotEmpty() && it.all { char -> char.isDigit() } }
    }

    /**
     * Adds leading zeroes to the input string to ensure it is at least 3 characters long.
     *
     * @param input The input string to be padded with leading zeroes.
     * @return The input string with leading zeroes added if its length is less than 3.
     *         If the input string is already 3 or more characters long, it is returned unchanged.
     */
    fun addLeadingZeroes(input: String): String {
        return if (input.length >= 3) {
            input
        } else {
            input.padStart(3, '0')
        }
    }

}