package bok.artenes.budgetcontrol

import androidx.lifecycle.LiveData
import androidx.room.*
import bok.artenes.budgetcontrol.budget.Budget

@Dao
interface BudgetsDao {

    @Query("SELECT * FROM budgets ORDER BY rowid DESC")
    fun getAll(): LiveData<List<Budget>>

    @Query("SELECT * FROM budgets WHERE uid = :uid")
    fun getBudget(uid: String): Budget?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(budget: Budget)

    @Update
    fun update(budget: Budget)

    @Delete
    fun delete(budget: Budget)

}