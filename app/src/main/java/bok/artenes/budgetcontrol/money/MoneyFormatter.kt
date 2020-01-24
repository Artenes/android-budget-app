package bok.artenes.budgetcontrol.money

import bok.artenes.budgetcontrol.Money
import java.text.NumberFormat
import java.util.*

class MoneyFormatter(private val locale: Locale = Locale.getDefault()) {

    fun format(money: Money): String {
        val moneyFormat = NumberFormat.getCurrencyInstance(locale)
        return moneyFormat.format(money.asDouble())
    }

}