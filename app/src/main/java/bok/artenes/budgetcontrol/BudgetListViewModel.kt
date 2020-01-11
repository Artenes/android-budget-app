package bok.artenes.budgetcontrol

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class BudgetListViewModel : ViewModel() {

    val budgets: LiveData<List<BudgetItem>>

    init {
        budgets = Transformations.map(
            BudgetsRepository.getBudgets()
        ) { list ->
            list.map { budget ->
                val value = "R$${(budget.value / 100f)}"
                BudgetItem(budget.id, budget.description, value)
            }
        }
    }

}