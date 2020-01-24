package bok.artenes.budgetcontrol.money

import org.junit.Assert.assertEquals
import org.junit.Test

class MoneyParserTest {

    private val parser = MoneyParser()

    @Test
    fun format_multipleValues() {
        assertEquals(0, parser.parse("").asCents())
        assertEquals(0, parser.parse("0").asCents())
        assertEquals(1, parser.parse("1").asCents())
        assertEquals(12, parser.parse("12").asCents())
        assertEquals(123, parser.parse("123").asCents())
        assertEquals(1234, parser.parse("1234").asCents())
        assertEquals(12345, parser.parse("12345").asCents())
        assertEquals(123456, parser.parse("123456").asCents())
        assertEquals(1234567, parser.parse("1234567").asCents())
        assertEquals(12345678, parser.parse("12345678").asCents())
        assertEquals(123456789, parser.parse("123456789").asCents())
        assertEquals(12345, parser.parse("0012345").asCents())
        assertEquals(1234500, parser.parse("1234500").asCents())
    }

}