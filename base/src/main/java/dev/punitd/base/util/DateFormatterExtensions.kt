package dev.punitd.base.util

import java.text.ParseException
import java.text.SimpleDateFormat

fun String.convertDate(inputFormat: SimpleDateFormat, outputFormat: SimpleDateFormat): String {
    return try {
        val date = inputFormat.parse(this)
        outputFormat.format(date)
    } catch (e: ParseException) {
        this
    }
}
