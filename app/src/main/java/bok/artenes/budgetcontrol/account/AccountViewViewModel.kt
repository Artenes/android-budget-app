package bok.artenes.budgetcontrol.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bok.artenes.budgetcontrol.R
import bok.artenes.budgetcontrol.Repository
import bok.artenes.budgetcontrol.SingleNotifyLiveData
import bok.artenes.budgetcontrol.money.Money
import java.util.*
import java.util.concurrent.Executors

class AccountViewViewModel(val uid: String?) : ViewModel() {

    private val executor = Executors.newSingleThreadExecutor()

    val name = MutableLiveData<String>()
    private val _nameError = MutableLiveData<Int>()
    val nameError: LiveData<Int>
        get() = _nameError

    val balance = MutableLiveData<Money>()

    private val _finishEdit = SingleNotifyLiveData<Void?>()
    val finishEdit: LiveData<Void?>
        get() = _finishEdit

    private val _confirmDeleteDialog = SingleNotifyLiveData<Void?>()
    val confirmDeleteDialog: LiveData<Void?>
        get() = _confirmDeleteDialog

    init {
        if (uid != null) {
            executor.execute {
                val account = Repository.getAccount(uid) as Account
                name.postValue(account.name)
                balance.postValue(account.balance)
            }
        }
        name.observeForever { _nameError.value = null }
    }

    fun isNew(): Boolean {
        return uid == null
    }

    fun save() {

        executor.execute {
            if (isValid()) {
                val name = this.name.value as String
                val balance = this.balance.value as Money

                val account: Account =
                    if (uid != null) {
                        val oldAccount = Repository.getAccount(uid) as Account
                        oldAccount.copy(
                            name = name,
                            balance = balance,
                            updatedAt = Calendar.getInstance()
                        )
                    } else {
                        Account(name = name, balance = balance)
                    }

                Repository.saveAccount(account)
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
                val account = Repository.getAccount(uid) as Account
                Repository.deleteAccount(account)
                _finishEdit.postValue(null)
            }
        }
    }

    private fun isValid(): Boolean {
        val name = this.name.value

        val isNameValid = name?.isNotEmpty() ?: false

        if (!isNameValid) {
            _nameError.postValue(R.string.required_field)
        }

        return isNameValid
    }

    class Factory(private val id: String?) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AccountViewViewModel(id) as T
        }

    }

}