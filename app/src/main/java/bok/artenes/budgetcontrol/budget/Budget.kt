package bok.artenes.budgetcontrol.budget

import androidx.room.Entity
import androidx.room.PrimaryKey
import bok.artenes.budgetcontrol.money.Money
import java.util.*

@Entity(tableName = "budgets")
data class Budget(
    @PrimaryKey val uid: String = UUID.randomUUID().toString(),
    val description: String,
    val value: Money,
    val date: Calendar,
    val createdAt: Calendar = Calendar.getInstance(),
    val updatedAt: Calendar = Calendar.getInstance()
)