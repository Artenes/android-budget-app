package bok.artenes.budgetcontrol.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import bok.artenes.budgetcontrol.Repository
import bok.artenes.budgetcontrol.money.MoneyFormatter

class AccountListViewModel : ViewModel() {

    val accounts: LiveData<List<AccountItem>>
    private val moneyFormatter: MoneyFormatter = MoneyFormatter()

    init {
        accounts = Transformations.map(Repository.getAccounts()) { list ->
            list.map { account ->
                val id = account.uid
                val name = account.name
                val balance = moneyFormatter.format(account.balance)
                AccountItem(id, name, balance)
            }
        }
    }
}