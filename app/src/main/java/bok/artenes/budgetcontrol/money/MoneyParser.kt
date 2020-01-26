package bok.artenes.budgetcontrol.money

class MoneyParser {

    fun parse(number: String): Money {
        val onlyDigits = number.filter { it.isDigit() }.trimStart('0')
        val value = if(onlyDigits.isNotEmpty()) onlyDigits.toLong() else 0
        return Money(value)
    }

}