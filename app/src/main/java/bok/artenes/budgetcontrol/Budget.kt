package bok.artenes.budgetcontrol

import java.util.*

data class Budget(val description: String, val value: Int, val id: String = UUID.randomUUID().toString())