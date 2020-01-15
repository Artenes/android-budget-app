package bok.artenes.budgetcontrol

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class MoneyTest {

    @Test
    fun format_multipleValues() {
        assertEquals("$0.00", formatAsMoney(""))
        assertEquals("$0.00", formatAsMoney("0"))
        assertEquals("$0.01", formatAsMoney("1"))
        assertEquals("$0.12", formatAsMoney("12"))
        assertEquals("$1.23", formatAsMoney("123"))
        assertEquals("$12.34", formatAsMoney("1234"))
        assertEquals("$123.45", formatAsMoney("12345"))
        assertEquals("$1,234.56", formatAsMoney("123456"))
        assertEquals("$12,345.67", formatAsMoney("1234567"))
        assertEquals("$123,456.78", formatAsMoney("12345678"))
        assertEquals("$1,234,567.89", formatAsMoney("123456789"))
        assertEquals("$123.45", formatAsMoney("0012345"))
        assertEquals("$12,345.00", formatAsMoney("1234500"))
    }

    private fun formatAsMoney(value: String): String {
        return Money(value).format(Locale.US)
    }

}