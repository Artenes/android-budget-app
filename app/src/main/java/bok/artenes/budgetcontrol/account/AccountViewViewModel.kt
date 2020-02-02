package bok.artenes.budgetcontrol.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bok.artenes.budgetcontrol.Repository
import bok.artenes.budgetcontrol.money.Money

class AccountViewViewModel : ViewModel() {

    private val _saveFinished = MutableLiveData<Boolean>(false)
    val saveFinished: LiveData<Boolean>
        get() = _saveFinished

    fun save(name: String, balance: Money) {
        val account = Account(name = name, balance = balance)
        Repository.saveAccount(account)
        _saveFinished.value = true
    }

}