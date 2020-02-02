package bok.artenes.budgetcontrol.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import bok.artenes.budgetcontrol.Repository

class AccountListViewModel : ViewModel() {

    val accounts: LiveData<List<AccountItem>>

    init {
        accounts = Transformations.map(Repository.getAccounts()) { list ->
            list.map { account -> AccountItem(account) }
        }
    }
}