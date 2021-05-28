package com.msomu.squareissues.util

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): String {
    val date = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.getDefault()).parse(this)
    val toDateFormat = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())
    return date?.let { toDateFormat.format(date) } ?: ""
}