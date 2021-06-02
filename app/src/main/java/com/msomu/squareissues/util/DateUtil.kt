package com.msomu.squareissues.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * The string that is in this format "yyyy-MM-dd'T'hh:mm:ss'Z'"
 * should be converted to this "MM-dd-yyyy" format
 * ...should return null is the format is not right
 * ...should return the string in correct format if its in the correct format
 */
fun String.toDate(): String? {
    return try {
        val date = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.getDefault()).parse(this)
        val toDateFormat = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())
        date?.let { toDateFormat.format(date) }
    } catch (parseException: ParseException) {
        null
    }
}