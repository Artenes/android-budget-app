package bok.artenes.budgetcontrol.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bok.artenes.budgetcontrol.Repository
import bok.artenes.budgetcontrol.SingleNotifyLiveData
import bok.artenes.budgetcontrol.money.Money
import java.util.*
import java.util.concurrent.Executors

class AccountViewViewModel(val uid: String?) : ViewModel() {

    private val executor = Executors.newSingleThreadExecutor()

    val name = MutableLiveData<String>()

    val balance = MutableLiveData<Money>()

    private val _saveFinished = SingleNotifyLiveData<Void?>()
    val saveFinished: LiveData<Void?>
        get() = _saveFinished

    private val _confirmDeleteDialog = SingleNotifyLiveData<Void?>()
    val confirmDeleteDialog: LiveData<Void?>
        get() = _confirmDeleteDialog

    init {
        if (uid != null) {
            executor.execute {
                val account = Repository.getAccount(uid)!!
                name.postValue(account.name)
                balance.postValue(account.balance)
            }
        }
    }

    fun save() {
        executor.execute {
            val account: Account =
                if (uid != null) {
                    val oldAccount = Repository.getAccount(uid)!!
                    oldAccount.copy(
                        name = name.value!!,
                        balance = balance.value!!,
                        updateDate = Calendar.getInstance()
                    )
                } else {
                    Account(name = name.value!!, balance = balance.value!!)
                }
            Repository.saveAccount(account)
            _saveFinished.postValue(null)
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
                _saveFinished.postValue(null)
            }
        }
    }

    class Factory(private val id: String?) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AccountViewViewModel(id) as T
        }

    }

}