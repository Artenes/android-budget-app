package bok.artenes.budgetcontrol.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bok.artenes.budgetcontrol.Repository
import bok.artenes.budgetcontrol.money.Money
import java.util.*
import java.util.concurrent.Executors

class AccountViewViewModel(val uid: String?) : ViewModel() {

    private val executor = Executors.newSingleThreadExecutor()

    val name = MutableLiveData<String>()

    val balance = MutableLiveData<Money>()

    private val _saveFinished = MutableLiveData<Boolean>()
    val saveFinished: LiveData<Boolean>
        get() = _saveFinished

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
            _saveFinished.postValue(true)
        }
    }

    class Factory(private val id: String?) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AccountViewViewModel(id) as T
        }

    }

}