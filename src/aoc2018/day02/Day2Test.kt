package aoc2018.day02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun testPart1() {
        assertEquals(solveFile("src/aoc2018/day02/input.txt"), 5976)
    }

    @Test
    fun testPart2() {
        assertEquals(solveFile2("src/aoc2018/day02/input.txt"), "xretqmmonskvzupalfiwhcfdb")
    }

}