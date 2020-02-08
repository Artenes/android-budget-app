package bok.artenes.budgetcontrol.budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import bok.artenes.budgetcontrol.Repository

class BudgetListViewModel : ViewModel() {

    val budgets: LiveData<List<BudgetItem>>

    init {
        budgets = Transformations.map(Repository.getBudgets()) { list ->
            list.map { budget -> BudgetItem(budget) }
        }
    }

}