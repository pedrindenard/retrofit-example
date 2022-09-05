package com.app.myapplication.util

import android.text.format.DateUtils
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object StringFormat {

    fun formatToDate(string: String): String {
        return if (string.isNotEmpty() && string.isNotBlank()) {
            try {
                val formatter: DateFormat = SimpleDateFormat("yyyy-MM-DD", Locale.ROOT)
                val date = formatter.parse(string) as Date
                DateFormat.getDateInstance(DateFormat.LONG).format(date)
            } catch (e: ParseException) {
                ""
            } catch (e: Exception) {
                ""
            }
        } else {
            ""
        }
    }

    fun formatToRuntime(string: String): String {
        val value = try { string.toLong() } catch (e: Exception) { 0 }
        return DateUtils.formatElapsedTime(value).replace(":", "h ") + " min"
    }

    fun formatTo2F(string: Float): String {
        return String.format("%.1f", string)
    }
}