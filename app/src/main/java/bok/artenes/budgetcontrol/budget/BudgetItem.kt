package bok.artenes.budgetcontrol.budget

import bok.artenes.budgetcontrol.date.DateFormatter
import bok.artenes.budgetcontrol.money.MoneyFormatter

class BudgetItem(val budget: Budget) {
    val uid: String = budget.uid
    val description = budget.description
    val value = MoneyFormatter().format(budget.value)
    val date = DateFormatter().format(budget.date)
}

