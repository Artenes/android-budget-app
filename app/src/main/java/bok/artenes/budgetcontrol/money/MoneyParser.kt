package bok.artenes.budgetcontrol.money

import bok.artenes.budgetcontrol.Money

class MoneyParser {

    fun parse(number: String): Money {
        val onlyDigits = number.filter { it.isDigit() }.trimStart('0')
        val value = if(onlyDigits.isNotEmpty()) onlyDigits.toLong() else 0
        return Money(value)
    }

}