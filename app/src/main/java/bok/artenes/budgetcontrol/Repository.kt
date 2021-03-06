package bok.artenes.budgetcontrol

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import bok.artenes.budgetcontrol.account.Account
import bok.artenes.budgetcontrol.budget.Budget
import java.util.concurrent.Executors

object Repository {

    private val executor = Executors.newSingleThreadExecutor()
    private lateinit var database: AppDatabase

    fun initDatabase(context: Context) {
        database = Room.databaseBuilder(context, AppDatabase::class.java, "budgets")
            .fallbackToDestructiveMigration().build()
    }

    fun getBudgets(): LiveData<List<Budget>> {
        return database.budgetsDao().getAll()
    }

    fun getBudget(uid: String): Budget? {
        return database.budgetsDao().getBudget(uid)
    }

    fun saveBudget(budget: Budget) {
        val exists = database.budgetsDao().getBudget(budget.uid) != null
        if (exists) {
            database.budgetsDao().update(budget)
        } else {
            database.budgetsDao().insert(budget)
        }
    }

    fun deleteBudget(budget: Budget) {
        database.budgetsDao().delete(budget)
    }

    fun getAccount(id: String): Account? {
        return database.accountsDao().getAccount(id)
    }

    fun getAccounts(): LiveData<List<Account>> {
        return database.accountsDao().getAll()
    }

    fun saveAccount(account: Account) {
        val exists = database.accountsDao().getAccount(account.uid) != null
        if (exists) {
            database.accountsDao().update(account)
        } else {
            database.accountsDao().insert(account)
        }
    }

    fun deleteAccount(account: Account) {
        database.accountsDao().delete(account)
    }

}
