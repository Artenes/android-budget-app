package bok.artenes.budgetcontrol

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bok.artenes.budgetcontrol.budget.Budget
import bok.artenes.budgetcontrol.money.Money
import java.util.*

class BudgetCreatorViewModel : ViewModel() {

    private val _saveFinished = MutableLiveData<Boolean>(false)
    val saveFinished: LiveData<Boolean>
        get() = _saveFinished

    fun save(description: String, price: Money, date: Calendar) {
        val budget = Budget(
            description = description,
            value = price,
            date = date
        )
        Repository.saveBudget(budget)
        _saveFinished.value = true
    }

}