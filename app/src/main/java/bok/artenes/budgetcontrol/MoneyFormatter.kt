package bok.artenes.budgetcontrol

import java.text.NumberFormat
import java.util.*

class MoneyFormatter(private val locale: Locale) {

    fun format(value: String): String {
        val valueToFormat = stringToFloat(value)
        val moneyFormat = NumberFormat.getCurrencyInstance(locale)
        return moneyFormat.format(valueToFormat)
    }

    private fun stringToFloat(number: String): Double {
        TODO("add limit for max DOUBLE")
        TODO("allow to add zeros ate the end, only trim at the start")
        val onlyDigits = number.filter { it.isDigit() }.trim('0')
        return when {
            onlyDigits.isEmpty() -> 0.0
            onlyDigits.length == 1 -> ".0${onlyDigits}".toDouble()
            onlyDigits.length == 2 -> ".${onlyDigits}".toDouble()
            else -> {
                val decimalIndex = onlyDigits.length - 2
                val value = onlyDigits.substring(0, decimalIndex)
                val decimals = onlyDigits.substring(decimalIndex)
                return "${value}.${decimals}".toDouble()
            }
        }
    }

}