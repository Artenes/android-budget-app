package bok.artenes.budgetcontrol

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class MoneyFormatterTest {

    private lateinit var formatter: MoneyFormatter

    @Before
    fun setUp() {
        formatter = MoneyFormatter(Locale.US)
    }

    @Test
    fun format_multipleValues() {
        assertEquals("$0.00", formatter.format(""))
        assertEquals("$0.00", formatter.format("0"))
        assertEquals("$0.01", formatter.format("1"))
        assertEquals("$0.12", formatter.format("12"))
        assertEquals("$1.23", formatter.format("123"))
        assertEquals("$12.34", formatter.format("1234"))
        assertEquals("$123.45", formatter.format("12345"))
        assertEquals("$1,234.56", formatter.format("123456"))
        assertEquals("$12,345.67", formatter.format("1234567"))
        assertEquals("$123,456.78", formatter.format("12345678"))
        assertEquals("$1,234,567.89", formatter.format("123456789"))
    }

}