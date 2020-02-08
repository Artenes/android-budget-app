package bok.artenes.budgetcontrol.money

class Money(private val value: Long) {

    fun asCents(): Long {
        return value
    }

    fun asDouble(): Double {
        return value / 100.0
    }

    fun isNotZero(): Boolean {
        return value != 0L
    }

}