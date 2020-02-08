package bok.artenes.budgetcontrol.date

import java.text.DateFormat
import java.util.*

class DateFormatter {

    fun format(date: Calendar): String {
        val formatter = DateFormat.getDateInstance(DateFormat.SHORT)
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        return formatter.format(date.time)
    }

}