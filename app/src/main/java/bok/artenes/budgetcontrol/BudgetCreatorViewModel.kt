package bok.artenes.budgetcontrol

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BudgetCreatorViewModel : ViewModel() {

    private val _saveFinished = MutableLiveData<Boolean>(false)
    val saveFinished: LiveData<Boolean>
        get() = _saveFinished

    fun save(description: String, price: String) {
        val priceInCents = (price.toFloat() * 100).toInt()
        BudgetsRepository.save(Budget(description, priceInCents))
        _saveFinished.value = true
    }

}