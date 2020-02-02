package bok.artenes.budgetcontrol.account

import bok.artenes.budgetcontrol.money.MoneyFormatter

class AccountItem(account: Account) {
    val id: String = account.uid
    val name: String = account.name
    val balance: String = MoneyFormatter().format(account.balance)
}