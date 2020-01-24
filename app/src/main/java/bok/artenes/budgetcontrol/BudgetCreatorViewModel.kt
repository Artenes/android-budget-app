package bok.artenes.budgetcontrol

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class BudgetCreatorViewModel : ViewModel() {

    private val _saveFinished = MutableLiveData<Boolean>(false)
    val saveFinished: LiveData<Boolean>
        get() = _saveFinished

    fun save(description: String, price: Money, date: Calendar) {
        val priceInCents = price.asCents()
        val dateInMilliseconds = date.timeInMillis
        val budget = Budget(description, priceInCents, dateInMilliseconds)
        BudgetsRepository.save(budget)
        _saveFinished.value = true
    }

}