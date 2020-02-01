package bok.artenes.budgetcontrol

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.DateFormat
import java.util.*

class BudgetListViewModel : ViewModel() {

    val budgets: LiveData<List<BudgetItem>>

    init {
        budgets = Transformations.map(
            Repository.getBudgets()
        ) { list ->
            list.map { budget ->
                val value = "R$${(budget.value / 100f)}"
                val formatter = DateFormat.getDateInstance(DateFormat.SHORT)
                formatter.timeZone = TimeZone.getTimeZone("UTC")
                val formattedDate = formatter.format(Date(budget.date))
                BudgetItem(budget.uid, budget.description, value, formattedDate)
            }
        }
    }

}