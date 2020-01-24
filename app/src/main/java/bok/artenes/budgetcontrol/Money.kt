package bok.artenes.budgetcontrol

class Money(private val value: Long) {

    fun asCents(): Long {
        return value
    }

    fun asDouble(): Double {
        return value / 100.0
    }

}