package bok.artenes.budgetcontrol

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Budget::class), version = 1)
abstract class BudgetsDatabase : RoomDatabase() {

    abstract fun budgetsDao(): BudgetsDao

}