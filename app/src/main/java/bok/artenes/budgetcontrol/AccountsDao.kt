package bok.artenes.budgetcontrol

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bok.artenes.budgetcontrol.account.Account

@Dao
interface AccountsDao {

    @Query("SELECT * FROM accounts ORDER BY rowid DESC")
    fun getAll(): LiveData<List<Account>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(account: Account)

}