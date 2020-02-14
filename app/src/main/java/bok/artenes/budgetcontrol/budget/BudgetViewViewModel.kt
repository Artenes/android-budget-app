package bok.artenes.budgetcontrol.budget

import androidx.lifecycle.*
import bok.artenes.budgetcontrol.R
import bok.artenes.budgetcontrol.Repository
import bok.artenes.budgetcontrol.SingleNotifyLiveData
import bok.artenes.budgetcontrol.SpinnerItem
import bok.artenes.budgetcontrol.money.Money
import java.util.*
import java.util.concurrent.Executors

class BudgetViewViewModel(private val uid: String?) : ViewModel() {

    private val executor = Executors.newSingleThreadExecutor()

    val accounts: LiveData<List<SpinnerItem>> = Transformations.map(Repository.getAccounts()) {
        it.map { SpinnerItem(it.uid, it.name) }
    }

    val description = MutableLiveData<String>()
    private val _descriptionError = MutableLiveData<Int>()
    val descriptionError: LiveData<Int>
        get() = _descriptionError

    val price = MutableLiveData<Money>()
    private val _priceError = MutableLiveData<Int>()
    val priceError: LiveData<Int>
        get() = _priceError

    val date = MutableLiveData<Calendar>()
    private val _dateError = MutableLiveData<Int>()
    val dateError: LiveData<Int>
        get() = _dateError

    val account = MutableLiveData<SpinnerItem>()
    private val _accountError = MutableLiveData<Int>()
    val accountError: LiveData<Int>
        get() = _accountError

    private val _finishEdit = SingleNotifyLiveData<Void?>()
    val finishEdit: LiveData<Void?>
        get() = _finishEdit

    private val _confirmDeleteDialog = SingleNotifyLiveData<Void?>()
    val confirmDeleteDialog: LiveData<Void?>
        get() = _confirmDeleteDialog

    init {
        executor.execute {
            if (uid != null) {
                val budget = Repository.getBudget(uid) as Budget
                description.postValue(budget.description)
                price.postValue(budget.value)
                date.postValue(budget.date)
            }
        }
        description.observeForever { _descriptionError.value = null }
        price.observeForever { _priceError.value = null }
        date.observeForever { _dateError.value = null }
        account.observeForever { _accountError.value = null }
    }

    fun isNew(): Boolean {
        return uid == null
    }

    fun save() {
        executor.execute {
            if (isValid()) {
                val description = this.description.value as String
                val price = this.price.value as Money
                val date = this.date.value as Calendar

                val budget = if (uid != null) {
                    val oldBudget = Repository.getBudget(uid) as Budget
                    oldBudget.copy(
                        description = description,
                        value = price,
                        date = date,
                        updatedAt = Calendar.getInstance()
                    )
                } else {
                    Budget(description = description, value = price, date = date)
                }

                Repository.saveBudget(budget)
                _finishEdit.postValue(null)
            }
        }
    }

    fun confirmDelete() {
        _confirmDeleteDialog.setValue(null)
    }

    fun delete() {
        executor.execute {
            if (uid != null) {
                val budget = Repository.getBudget(uid) as Budget
                Repository.deleteBudget(budget)
                _finishEdit.postValue(null)
            }
        }
    }

    private fun isValid(): Boolean {
        val description = this.description.value
        val price = this.price.value
        val date = this.date.value

        val isDescriptionValid = description?.isNotEmpty() ?: false
        val isPriceValid = price?.isNotZero() ?: false
        val isDateValid = date?.isSet(Calendar.DATE) ?: false

        if (!isDescriptionValid) {
            _descriptionError.postValue(R.string.required_field)
        }

        if (!isPriceValid) {
            _priceError.postValue(R.string.required_field)
        }

        if (!isDateValid) {
            _dateError.postValue(R.string.required_field)
        }

        return isDescriptionValid && isPriceValid && isDateValid
    }

    class Factory(private val uid: String?) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BudgetViewViewModel(uid) as T
        }

    }

}