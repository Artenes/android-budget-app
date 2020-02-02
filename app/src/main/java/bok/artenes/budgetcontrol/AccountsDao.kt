package bok.artenes.budgetcontrol

import androidx.lifecycle.LiveData
import androidx.room.*
import bok.artenes.budgetcontrol.account.Account

@Dao
interface AccountsDao {

    @Query("SELECT * FROM accounts WHERE uid = :id")
    fun getAccount(id: String): Account?

    @Query("SELECT * FROM accounts ORDER BY rowid DESC")
    fun getAll(): LiveData<List<Account>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(account: Account)

    @Update
    fun update(account: Account)

}