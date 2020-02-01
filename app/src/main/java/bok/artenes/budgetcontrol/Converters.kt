package bok.artenes.budgetcontrol

import androidx.room.TypeConverter
import bok.artenes.budgetcontrol.money.Money
import java.util.*

class Converters {

    @TypeConverter
    fun toMoney(value: Long) : Money {
        return Money(value)
    }

    @TypeConverter
    fun fromMoney(value: Money) : Long {
        return value.asCents()
    }

    @TypeConverter
    fun toCalendar(value: Long) : Calendar {
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar.timeInMillis = value
        return calendar
    }

    @TypeConverter
    fun fromCalendar(value: Calendar) : Long {
        return value.timeInMillis
    }

}