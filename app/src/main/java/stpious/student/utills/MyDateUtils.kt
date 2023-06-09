package prajna.app.utills

import android.annotation.SuppressLint
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun getDuration(startTime: Long, endTime: Long): String {
    var minutes = getMinutes(startTime, endTime)
    var hours = 0
    while (minutes >= 60) {
        hours++
        minutes -= 60
    }
    val min = if (minutes == 1) "min" else "mins"
    val hour = if (hours > 1) "hrs" else "hr"
    var finalDate = ""
    finalDate = if (hours == 0) {
        "$minutes $min ago"
    } else if (hours in 1..23) {
        "$hours $hour ago"
    } else {
        val time = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(Date(startTime))
        convertDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "dd-MM-yy", time)
    }

    return finalDate
}

fun getDurationLeft(startTime: Long, endTime: Long): String {
    var minutes = getMinutes(startTime, endTime)
    var hours = 0
    while (minutes >= 60) {
        hours++
        minutes -= 60
    }
    val min = if (minutes == 1) "min" else "mins"
    val hour = if (hours > 1) "hrs" else "hr"
    var finalDate = ""
    if (hours == 0) {
        finalDate = "$minutes $min left"
    } else if (hours in 1..24) {
        finalDate = "$hours $hour left"
    }

    return finalDate
}

fun convertDateFormat(FormFormat: String, ToFormat: String, value: String): String {
    var returnValue = value
    val formFormat = SimpleDateFormat(FormFormat, Locale.getDefault())
    val toFormat = SimpleDateFormat(ToFormat, Locale.getDefault())
    returnValue = toFormat.format(formFormat.parse(value))
    return returnValue
}

fun getMinutes(start: Long, end: Long): Int {
    val durationMillis = end - start
    val addedMinute =
        if (Math.ceil((durationMillis % DateUtils.MINUTE_IN_MILLIS).toDouble()) > 0) 1 else 0
    return Math.floor(durationMillis / DateUtils.MINUTE_IN_MILLIS.toDouble())
        .toInt() + addedMinute
}