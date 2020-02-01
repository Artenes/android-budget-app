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
    val createDate: Calendar = Calendar.getInstance(),
    val updateDate: Calendar = Calendar.getInstance()
)