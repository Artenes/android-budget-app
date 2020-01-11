package bok.artenes.budgetcontrol

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "budgets")
data class Budget(
    val description: String,
    val value: Int,
    @PrimaryKey val uid: String = UUID.randomUUID().toString()
)