package bok.artenes.budgetcontrol.account

import androidx.room.Entity
import androidx.room.PrimaryKey
import bok.artenes.budgetcontrol.money.Money
import java.util.*

@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey val uid: String = UUID.randomUUID().toString(),
    val name: String,
    val balance: Money,
    val createdAt: Calendar = Calendar.getInstance(),
    val updatedAt: Calendar = Calendar.getInstance()
)