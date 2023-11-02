package com.college.tangkis.util

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

fun createTimeStamp(format: DateFormat): String = run {
    val date = java.util.Date()
    val formatter = SimpleDateFormat(format.format, Locale("id", "ID"))
    formatter.timeZone = java.util.TimeZone.getTimeZone("Asia/Jakarta")
    formatter.format(date)
}

@RequiresApi(Build.VERSION_CODES.O)
fun List<String>.sortDate(): List<String> = run {
    val formatter = DateTimeFormatter.ofPattern(DateFormat.DATE.format)
    sortedByDescending { LocalDate.parse(it, formatter) }
}

@SuppressLint("SimpleDateFormat")
infix fun String.gapBetween(date: String) = run {
    val dateFormat = SimpleDateFormat(DateFormat.DATE.format)
    val date1 = dateFormat.parse(this)
    val date2 = dateFormat.parse(date)

    val diffInMillis = abs(date2.time - date1.time)
    TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
}

enum class DateFormat(val format: String) {
    DATE("dd MMMM yyyy"),
    DATE_TIME("${DATE.format}, HH:mm"),
    DAY_DATE("EEEE dd MMMM yyyy"),
    DAY_DATE_TIME("${DAY_DATE.format}, HH:mm"),
}