package bok.artenes.budgetcontrol

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BudgetListViewModel : ViewModel() {

    private val _budgets = MutableLiveData<List<BudgetItem>>()
    val budgets: LiveData<List<BudgetItem>>
        get() = _budgets

    init {
        val budgets = listOf(
            Budget("Descirption A", 9000),
            Budget("Descirption B", 10000),
            Budget("Descirption C", 11000)
        )
        _budgets.value = budgets.map {
            val value = "R$${(it.value / 100)}"
            BudgetItem(it.id, it.description, value)
        }
    }

}