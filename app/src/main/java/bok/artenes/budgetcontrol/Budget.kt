package bok.artenes.budgetcontrol

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "budgets")
data class Budget(
    val description: String,
    val value: Long,
    val date: Long,
    @PrimaryKey val uid: String = UUID.randomUUID().toString()
)