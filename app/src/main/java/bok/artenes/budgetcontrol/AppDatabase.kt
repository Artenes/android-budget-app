package bok.artenes.budgetcontrol

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import bok.artenes.budgetcontrol.account.Account

@Database(entities = [Budget::class, Account::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun budgetsDao(): BudgetsDao

    abstract fun accountsDao(): AccountsDao

}