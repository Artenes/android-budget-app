package bok.artenes.budgetcontrol

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BudgetsDao {

    @Query("SELECT * FROM budgets ORDER BY rowid DESC")
    fun getAll(): LiveData<List<Budget>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(budget: Budget)

}