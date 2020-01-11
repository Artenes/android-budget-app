package bok.artenes.budgetcontrol

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object BudgetsRepository {

    private val budgets = mutableListOf(
        Budget("Descirption A", 9010),
        Budget("Descirption B", 10050),
        Budget("Descirption C", 11050)
    ).also { it.reverse() }

    private val budgetsLiveData = MutableLiveData(budgets)

    fun getBudgets() : LiveData<List<Budget>> {
        return budgetsLiveData as LiveData<List<Budget>>
    }

    fun save(budget: Budget) {
        budgets.add(0, budget)
        budgetsLiveData.value = budgets
    }

}