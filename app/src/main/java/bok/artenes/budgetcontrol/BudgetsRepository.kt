package bok.artenes.budgetcontrol

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.concurrent.Executors

object BudgetsRepository {

    private val executor = Executors.newSingleThreadExecutor()
    private lateinit var database: BudgetsDatabase

    fun initDatabase(context: Context) {
        database = Room.databaseBuilder(context, BudgetsDatabase::class.java, "budgets")
            .fallbackToDestructiveMigration().build()
    }

    fun getBudgets(): LiveData<List<Budget>> {
        return database.budgetsDao().getAll()
    }

    fun save(budget: Budget) {
        executor.execute {
            database.budgetsDao().insert(budget)
        }
    }

}
