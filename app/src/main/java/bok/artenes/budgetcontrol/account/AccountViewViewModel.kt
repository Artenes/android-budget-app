package bok.artenes.budgetcontrol.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bok.artenes.budgetcontrol.Repository
import bok.artenes.budgetcontrol.money.Money
import java.util.*
import java.util.concurrent.Executors

class AccountViewViewModel(val id: String?) : ViewModel() {

    private val executor = Executors.newSingleThreadExecutor()

    private val _saveFinished = MutableLiveData<Boolean>(false)
    val saveFinished: LiveData<Boolean>
        get() = _saveFinished

    private val _account = MutableLiveData<AccountItem>()
    val account: LiveData<AccountItem>
        get() = _account

    init {
        if (id != null) {
            executor.execute {
                val account = Repository.getAccount(id)!!
                val item = AccountItem(account)
                _account.postValue(item)
            }
        }
    }

    fun save(name: String, balance: Money) {
        executor.execute {
            val account: Account =
                if (id != null) {
                    val oldAccount = Repository.getAccount(id)!!
                    oldAccount.copy(
                        name = name,
                        balance = balance,
                        updateDate = Calendar.getInstance()
                    )
                } else {
                    Account(name = name, balance = balance)
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