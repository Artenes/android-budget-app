package bok.artenes.budgetcontrol

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import bok.artenes.budgetcontrol.account.Account
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

    fun saveBudget(budget: Budget) {
        executor.execute {
            database.budgetsDao().insert(budget)
        }
    }

    fun getAccounts(): LiveData<List<Account>> {
        return database.accountsDao().getAll()
    }

    fun saveAccount(account: Account) {
        executor.execute {
            database.accountsDao().insert(account)
        }
    }

}
