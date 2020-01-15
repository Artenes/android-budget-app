package bok.artenes.budgetcontrol

import java.text.NumberFormat
import java.util.*

class Money(initialValue: String) {

    private val value: Double

    init {
        value = stringToFloat(initialValue)
    }

    fun format(locale: Locale = Locale.getDefault()): String {
        val moneyFormat = NumberFormat.getCurrencyInstance(locale)
        return moneyFormat.format(value)
    }

    private fun stringToFloat(number: String): Double {
        val onlyDigits = number.filter { it.isDigit() }.trimStart('0')
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